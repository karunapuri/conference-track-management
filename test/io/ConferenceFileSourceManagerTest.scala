package io

import java.io.FileNotFoundException

import org.junit.Assert
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

class ConferenceFileSourceManagerTest extends FunSpec with Matchers with MockitoSugar {

  val manager = new ConferenceFileSourceManager

  describe("FileNotFound Exception") {
    it("should throw File Not Found Exception for invalid file") {
      val thrown = the[FileNotFoundException] thrownBy manager.fetchTalks("input-talks-random-file.txt")
      thrown.getMessage should equal("input-talks-random-file.txt (No such file or directory)")
    }
  }

  describe("NumberFormatException Exception") {
    it("should throw NumberFormatException Exception for invalid talks") {
      val thrown = the[NumberFormatException] thrownBy manager.fetchTalks("input-invalid-talks.txt")
      thrown.getMessage should equal("For input string: \"\"")
    }
  }

  describe("Fetch valid Talk File"){
    it("should fetch valid talk file and its size"){
      val talks = manager.fetchTalks("input-talks.txt")
      Assert.assertEquals(19, talks.size)
    }

    it("should fetch zero size for empty talk file"){
      val talks = manager.fetchTalks("input-empty-talks.txt")
      Assert.assertEquals(0, talks.size)
    }
  }
}
