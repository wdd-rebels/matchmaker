package model

import play.api.libs.json.Json

case class Availability(`type`: String)

object Availability {
  implicit val oformat = Json.format[Availability]
}
