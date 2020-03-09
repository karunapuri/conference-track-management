package dao

import java.util.Calendar

class Event(var startTime: Calendar, var title: String, var durationInMinutes: Int) {

  def getStartTime: Calendar = startTime

  def getDurationInMinutes = durationInMinutes

  def getTitle = title
}

