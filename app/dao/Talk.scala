package dao

class Talk(var title: String, var durationInMinutes: Int) {

  def getDurationInMinutes = durationInMinutes
  def getTitle = title
}