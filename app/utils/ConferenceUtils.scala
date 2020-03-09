package utils

import java.util

import dao.Talk
import java.util.Calendar
import scala.collection.JavaConverters._

object ConferenceUtils {
  def getCalendarTime(hours: Int, minutes: Int): Calendar = {
    val cal = Calendar.getInstance
    cal.set(Calendar.HOUR_OF_DAY, hours)
    cal.set(Calendar.MINUTE, minutes)
    cal
  }

  def getNextStartTime(currentStartTime: Calendar, currentTalk: Talk): Calendar = {
    val newTime = Calendar.getInstance
    newTime.set(Calendar.HOUR_OF_DAY, currentStartTime.get(Calendar.HOUR_OF_DAY))
    newTime.set(Calendar.MINUTE, currentStartTime.get(Calendar.MINUTE))
    newTime.add(Calendar.MINUTE, currentTalk.getDurationInMinutes)
    newTime
  }

  def printTalks(talkList: util.ArrayList[Talk]): Unit = {
    println("--------------------------------------------------------")
    println("Input talks:")
    println("")
    for (talk <- talkList.asScala) { // Print the prepared talk's title for this Track
      println(talk.getTitle + " " + talk.getDurationInMinutes)
    }
    println("--------------------------------------------------------")
  }
}

