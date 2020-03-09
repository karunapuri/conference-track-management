package controllers

import java.io.FileNotFoundException
import java.util
import java.util.Collections

import com.google.inject.Singleton
import config.ConferenceManagementConfig
import dao._
import enums.DataInputEnum.DataInputEnum
import enums.{DataInputEnum, DataOutputEnum}
import enums.DataOutputEnum.DataOutputEnum
import exceptions.{UnsupportedDestinationException, UnsupportedSourceException}
import io.{ConferenceFileSourceManager, ConsoleOutputManager}
import play.api.mvc._
import play.mvc.Controller
import utils.ConferenceUtils

@Singleton
class ConferenceController extends Controller{

  def index() = Action{
    play.api.mvc.Results.Ok("Conference Track Management")
  }

  @throws[UnsupportedSourceException]
  def fetchTalksListFromSource(dataSourceEnum: DataInputEnum) = {
    if (dataSourceEnum.equals(DataInputEnum.FILE)) {
      val sourceManager = new ConferenceFileSourceManager
      try
      sourceManager.fetchTalks
      catch {
        case e: FileNotFoundException =>
          null
      }
    }
    else throw new UnsupportedSourceException("Source type not valid")
  }

  @throws[UnsupportedDestinationException]
  def outputConferenceSchedule(conference: Conference, dataOutputEnum: DataOutputEnum) = {
    if (dataOutputEnum.equals(DataOutputEnum.CONSOLE)) {
      val outputManager = new ConsoleOutputManager
      outputManager.printSchedule(conference)
    }
    else throw new UnsupportedDestinationException("Destination not valid.")
  }

  def processAndScheduleTalks(talkList: util.ArrayList[Talk]): Conference = {
    val conference = new Conference()
    val talksCompare = new TalksCompare()
    Collections.sort(talkList, talksCompare)
    var trackCount = 0
    while ( {0 != talkList.size}) {
      val morningSlot = new Slot(ConferenceManagementConfig.MORNING_SLOT_DURATION_MINUTES, ConferenceManagementConfig.TRACK_START_TIME)
      fillSlot(morningSlot, talkList)
      val lunchSlot = new Slot(ConferenceManagementConfig.LUNCH_DURATION_MINUTES, ConferenceManagementConfig.LUNCH_START_TIME)
      lunchSlot.addEvent(new Lunch)
      val afternoonSlot = new Slot(ConferenceManagementConfig.AFTERNOON_SLOT_DURATION_MINUTES, ConferenceManagementConfig.POST_LUNCH_SLOT_START_TIME)
      fillSlot(afternoonSlot, talkList)
      val networkingSlot = new Slot(ConferenceManagementConfig.NETWORKING_DURATION_MINUTES, ConferenceManagementConfig.NETWORKING_START_TIME)
      networkingSlot.addEvent(new Networking)
      val track = new Track({
        trackCount += 1; trackCount
      })
      track.addSlot(morningSlot)
      track.addSlot(lunchSlot)
      track.addSlot(afternoonSlot)
      track.addSlot(networkingSlot)

      conference.addTrack(track)
    }
    conference
  }

  private def fillSlot(slot: Slot, talks: util.ArrayList[Talk]) = {
    var currentStartTime = slot.getStartTime
    val talksIterator = talks.iterator
    while ( {
      talksIterator.hasNext
    }) {
      val talk = talksIterator.next
      if (slot.hasRoomFor(talk)) {
        slot.addEvent(new Event(currentStartTime, talk.getTitle, talk.getDurationInMinutes))
        currentStartTime = ConferenceUtils.getNextStartTime(currentStartTime, talk)
        talksIterator.remove()
      }
    }
  }
}

