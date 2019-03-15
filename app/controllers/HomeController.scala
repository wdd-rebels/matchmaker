package controllers

import dao.{CharityDao, VolunteerDao}
import javax.inject._
import model.{Availability, Charity, Project}
import play.api.libs.json._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, charityDao: CharityDao, volunteerDao: VolunteerDao) extends AbstractController(cc) {

  def index() = Action(Redirect("/assets/pages/index.html"))

  def charities() = Action {Ok(Json.toJson(charityDao.getCharities()))}

  def volunteers() = Action {Ok(Json.toJson(volunteerDao.getVolunteers()))}

  def suggestedCauses() = Action {Ok(Json.toJson(charityDao.getSuggestedCauses()))}

  def suggestedSkills() = Action {Ok(Json.toJson(charityDao.getSuggestedSkills()))}

  def suggestedQualifications() = Action {Ok(Json.toJson(List("BA English")))}

  case class Volunteer(name: String, email: String, causes: List[String], location: String, skills: List[String],
                       qualifications: List[String], availability: Availability, experienceLevel: String, summary: String)

  def addCharity() = Action{ req =>
    Ok()
  }

  def addVolunteer() = Action{ req =>
    Ok()
  }
}

