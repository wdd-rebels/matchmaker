package model

import play.api.libs.json.Json

case class CharityProject(
                    name: String,
                    email: String,
                    website: String,
                    location: String,
                    causes: List[String],
                    title: String,
                    description: String,
                    availability: String,
                    skills: List[String]
                  )

object CharityProject {
  implicit val oformat = Json.format[CharityProject]
}
