package model

import play.api.libs.json.Json

case class Project(title: String, description: String, availability: Availability, tags: List[String])

object Project {
  implicit val oformat = Json.format[Project]
}
