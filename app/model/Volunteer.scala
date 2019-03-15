package model

import play.api.libs.json.Json

case class Volunteer(
                      name: String,
                      email: String,
                      causes: List[String],
                      location: String,
                      skills: List[String],
                      qualifications: List[String],
                      availability: String,
                      experienceLevel: String,
                      summary: String
                    )

object Volunteer {
  implicit val oformat = Json.format[Volunteer]
}
