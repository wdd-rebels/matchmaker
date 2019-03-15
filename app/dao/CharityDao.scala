package dao

import model.Project
import play.api.libs.json.Json

import scala.io.Source

class CharityDao {
  def getCharities(): List[Project] = {
    val projectsRaw = Source.fromResource("charities.json").mkString
    val projectsJson = Json.parse(projectsRaw)
    projectsJson.validate[List[Project]].get
  }
}
