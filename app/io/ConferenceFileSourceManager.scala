package io

import java.io._
import java.util

import config.ConferenceManagementConfig
import dao.Talk

class ConferenceFileSourceManager {
  @throws[FileNotFoundException]
  def fetchTalks(fileName: String) : util.ArrayList[Talk]= {
    var fileStream: FileInputStream = null
    val talkList = new util.ArrayList[Talk]
    try fileStream = new FileInputStream(fileName)
    catch {
      case e: FileNotFoundException =>
        println("Input file specified not found : " + ConferenceManagementConfig.TALKS_INPUT_FILE + ". Make sure the file exists")
        throw e
    }
    val br = new BufferedReader(new InputStreamReader(fileStream))
    var strLine = ""
    var intMinutes = 0
    try {
      while ({(strLine = br.readLine) != ""}) {
          val title = strLine.substring(0, strLine.lastIndexOf(" "))
          val minutesString = strLine.substring(strLine.lastIndexOf(" ") + 1)
          val minutes = strLine.replaceAll("\\D+", "")

          if (minutesString.equals(ConferenceManagementConfig.LIGHTNING_TALK))
            intMinutes = ConferenceManagementConfig.LIGHTNING_TALK_DURATION_MINUTES
          else {
            try intMinutes = minutes.toInt
            catch {
              case e: NumberFormatException => println("Could not parse the line : " + strLine)
                throw e
            }
          }
        val singleTalk = new Talk(title, intMinutes)
        talkList.add(singleTalk)
      }
      try {
        fileStream.close()
        br.close()
      } catch {case e: IOException => println(e.getMessage)}
    }
      catch {
        case e: IOException => e
        case e: NullPointerException => e
      }

    return talkList
    }

    @throws[FileNotFoundException]
    def fetchTalks: util.ArrayList[Talk] = {
      fetchTalks(ConferenceManagementConfig.TALKS_INPUT_FILE)
    }
  }
