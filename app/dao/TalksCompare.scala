package dao

import java.util.Comparator

class TalksCompare extends Comparator[Talk] {
  override def compare(t1: Talk, t2: Talk) = if (t1.getDurationInMinutes < t2.getDurationInMinutes) 1  else -1
}
