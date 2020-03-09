package dao

import java.util

class Track(var trackId: Int) {

  private val slots = new util.ArrayList[Slot]

  def getSlots = slots
  def addSlot(slot: Slot) = slots.add(slot)
  def getTrackId = trackId
}