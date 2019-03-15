package controllers

import model.{CharityProject, Volunteer}
import play.api.data.Forms._
import play.api.data._

trait Forms {

  val volunteerForm = Form(
    mapping(
      "name" -> text,
      "email" -> text,
      "causes" -> list(text),
      "location" -> text,
      "skills" -> list(text),
      "qualifications" -> list(text),
      "availability" -> text,
      "experienceLevel" -> text,
      "summary" -> text,
    )(Volunteer.apply)(Volunteer.unapply)
  )

  val charityForm = {
    null: Form[CharityProject]
    Form(
      mapping(
        "name" -> text,
        "email" -> text,
        "website" -> text,
        "location" -> text,
        "causes" -> list(text),
        "title" -> text,
        "description" -> text,
        "availability" -> text,
        "skills" -> list(text),
      )(CharityProject.apply)(CharityProject.unapply)
    )
  }

}
