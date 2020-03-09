package controllers

import java.util

import dao.{Conference, Talk}
import enums.{DataInputEnum, DataOutputEnum}
import exceptions.{UnsupportedDestinationException, UnsupportedSourceException}
import utils.ConferenceUtils


object Conference {
  def main(args: Array[String]): Unit = {
    val conferenceManager = new ConferenceController
    var talkList = new util.ArrayList[Talk]
    try
      talkList= conferenceManager.fetchTalksListFromSource(DataInputEnum.FILE)
    catch {
      case e: UnsupportedSourceException =>
        println(e.getMessage)
    }
    if (talkList == null || (talkList.size == 0)) return
     ConferenceUtils.printTalks(talkList)
    val conference: Conference = conferenceManager.processAndScheduleTalks(talkList)
    try
      conferenceManager.outputConferenceSchedule(conference, DataOutputEnum.CONSOLE)
    catch {
      case e: UnsupportedDestinationException =>
        println(e.getMessage)
    }
  }
}
