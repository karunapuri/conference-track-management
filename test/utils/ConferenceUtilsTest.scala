package utils

import org.scalatest.{FunSpec, Matchers}
import org.scalatest.mockito.MockitoSugar
import org.junit.Assert

class ConferenceUtilsTest extends FunSpec with Matchers with MockitoSugar {

  describe("Get Calendar Time"){
    it("should test getCalendarTime"){
      import java.util.Calendar
      var cal1 = ConferenceUtils.getCalendarTime(4, 60)

      var cal2 = Calendar.getInstance
      cal2.set(Calendar.HOUR_OF_DAY, 4)
      cal2.set(Calendar.MINUTE, 60)
      Assert.assertEquals(cal1.get(Calendar.HOUR_OF_DAY), cal2.get(Calendar.HOUR_OF_DAY))
      Assert.assertEquals(cal1.get(Calendar.MINUTE), cal2.get(Calendar.MINUTE))

      cal1 = ConferenceUtils.getCalendarTime(5, 50)
      cal2 = Calendar.getInstance
      cal2.set(Calendar.HOUR_OF_DAY, 5)
      cal2.set(Calendar.MINUTE, 50)

      Assert.assertEquals(cal1.get(Calendar.HOUR_OF_DAY), cal2.get(Calendar.HOUR_OF_DAY))
      Assert.assertEquals(cal1.get(Calendar.MINUTE), cal2.get(Calendar.MINUTE))
    }
  }

  describe("Get Next Start Time"){
    it("should test getNextStartTime"){
      import dao.Talk
      import java.util.Calendar
      val currentStartTime = ConferenceUtils.getCalendarTime(5, 50)
      val talk = new Talk("test talk 1", 40)

      val nextStartTimeManual = ConferenceUtils.getCalendarTime(6, 30)
      val nextStartTimeCalculated = ConferenceUtils.getNextStartTime(currentStartTime, talk)

      Assert.assertEquals(nextStartTimeManual.get(Calendar.HOUR_OF_DAY), nextStartTimeCalculated.get(Calendar.HOUR_OF_DAY))
      Assert.assertEquals(nextStartTimeManual.get(Calendar.MINUTE), nextStartTimeCalculated.get(Calendar.MINUTE))
    }
  }

}
