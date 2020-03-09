package io

import config.ConferenceManagementConfig
import dao.Conference

import scala.collection.JavaConverters._

class ConsoleOutputManager {
  def printSchedule(conference: Conference) = {
    val dateFormat = ConferenceManagementConfig.DATE_FORMAT
    println("Output Conference Schedule :")
    println("--------------------------------------------------------")
    for (track <- conference.getTracks.asScala) {
      println("Track " + track.getTrackId + ":")
      val slots = track.getSlots
      for (slot <- slots.asScala) {
        for (event <- slot.getEvents.asScala)
          println(dateFormat.format(event.getStartTime.getTime) + " " + event.getTitle + " " + event.getDurationInMinutes + "min")
      }
      println("--------------------------------------------------------")
    }
  }
}
