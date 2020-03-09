package config

import java.text.SimpleDateFormat
import java.util.Calendar

import utils.ConferenceUtils

object ConferenceManagementConfig {
  val TALKS_INPUT_FILE = "input-talks.txt"
  val DATE_FORMAT = new SimpleDateFormat("hh:mm")
  val LIGHTNING_TALK = "lightning"
  val TOTAL_TRACK_DURATION_MINUTES = 420
  val MORNING_SLOT_DURATION_MINUTES = 180
  val AFTERNOON_SLOT_DURATION_MINUTES = 240
  val LIGHTNING_TALK_DURATION_MINUTES = 5
  var TRACK_START_TIME: Calendar = ConferenceUtils.getCalendarTime(9, 0)
  var LUNCH_START_TIME: Calendar = ConferenceUtils.getCalendarTime(12, 0)
  var POST_LUNCH_SLOT_START_TIME: Calendar = ConferenceUtils.getCalendarTime(13, 0)
  var NETWORKING_START_TIME: Calendar = ConferenceUtils.getCalendarTime(17, 0)
  val LUNCH_DURATION_MINUTES = 60
  val NETWORKING_DURATION_MINUTES = 60
}

