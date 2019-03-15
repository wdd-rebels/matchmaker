package controllers

import dao.{CharityProjectDao, MatchingService, VolunteerDao}
import javax.inject._
import play.api.libs.json._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, charityDao: CharityProjectDao, volunteerDao: VolunteerDao, matchingService: MatchingService) extends AbstractController(cc) with Forms {

  def index() = Action(Redirect("/assets/pages/index.html"))

  def charities() = Action {Ok(Json.toJson(charityDao.getCharities()))}

  def volunteers() = Action {Ok(Json.toJson(volunteerDao.getVolunteers()))}

  def suggestedCauses() = Action {Ok(Json.toJson(charityDao.getSuggestedCauses()))}

  def suggestedSkills() = Action {Ok(Json.toJson(charityDao.getSuggestedSkills()))}

  def suggestedQualifications() = Action {Ok(Json.toJson(List("BA English")))}

  def addCharity() = Action{ implicit req =>
    charityForm.bindFromRequest().value.foreach(charityDao.add)
    Ok(views.html.newCharityProject())
  }

  def addVolunteer() = Action{ implicit req =>
    val form = volunteerForm.bindFromRequest()
    form.errors.foreach(println)
    val volunteer = form.value.get
    volunteerDao.add(volunteer)
    val matchingProjects = matchingService.matchingCharities(volunteer)
    val matchesPage = views.html.matches(s"Matches for  ${volunteer.name}", matchingProjects)
    Ok(matchesPage)
  }
}

