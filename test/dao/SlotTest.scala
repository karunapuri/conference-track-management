package dao

import org.scalatest.{FunSpec, Matchers}
import org.scalatest.mockito.MockitoSugar
import utils.ConferenceUtils
import org.junit.Assert


class SlotTest extends FunSpec with Matchers with MockitoSugar {

  describe("check slot"){
    it("should check room for talk"){
      val slotStartTime = ConferenceUtils.getCalendarTime(2, 20)
      val slot = new Slot(50, slotStartTime)

      val talk1 = new Talk("Valid Talk", 45)
      Assert.assertTrue(slot.hasRoomFor(talk1))

      val talk2 = new Talk("InValid Talk", 60)
      Assert.assertFalse(slot.hasRoomFor(talk2))
    }
  }
}
