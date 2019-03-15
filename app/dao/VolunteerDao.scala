package dao

import model.Volunteer
import play.api.libs.json.Json

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

class VolunteerDao {

  private val volunteers: ListBuffer[Volunteer] = {
    val projectsRaw = Source.fromResource("volunteers.json").mkString
    val projectsJson = Json.parse(projectsRaw)
    projectsJson.validate[ListBuffer[Volunteer]].get
  }

  def getVolunteers(): List[Volunteer] = {
    volunteers.toList
  }

  def add(volunteer: Volunteer): Unit = {
    volunteers.append(volunteer)
  }
}
