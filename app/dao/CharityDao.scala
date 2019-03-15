package dao

import model.{Charity, Project}
import play.api.libs.json.Json

import scala.io.Source

class CharityDao {
  def getCharities(): List[Charity] = {
    val projectsRaw = Source.fromResource("charities.json").mkString
    val projectsJson = Json.parse(projectsRaw)
    projectsJson.validate[List[Charity]].get
  }
}
