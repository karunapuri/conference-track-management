package dao

import java.util

class Conference() {

  val tracks = new util.ArrayList[Track]
  def getTracks = tracks
  def addTrack(track: Track) = tracks.add(track)
}



