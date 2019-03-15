package model

import play.api.libs.json.Json

case class Charity(name: String, email: String, website: String, cause: String, location: String, tags: List[String], projects: List[Project])

object Charity {
  implicit val oformat = Json.format[Charity]
}
