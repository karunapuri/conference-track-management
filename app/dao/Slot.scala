package dao

import java.util
import java.util.Calendar

class Slot(var remainingDuration: Int, var startTime: Calendar) {

  private val events = new util.ArrayList[Event]

  def getEvents = events
  def addEvent(event: Event) = {
    events.add(event)
    remainingDuration -= event.getDurationInMinutes
  }
  def getStartTime: Calendar = startTime
  def hasRoomFor(talk: Talk) = remainingDuration >= talk.getDurationInMinutes
}

