package controllers

import dao.CharityDao
import javax.inject._
import model.{Availability, Charity, Project}
import play.api.libs.json._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, charityDao: CharityDao) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }


  val allProjects: List[Project] = List(
    //    Project("test1", "description"),
    //    Project("test2", "description")
  )

  def charities() = Action { implicit req =>
    Ok(
      Json.toJson[List[Charity]](
        charityDao.getCharities()
      )
    )
  }

  def suggestedCauses() = Action {
    Ok {
      Json.toJson {
        val allCauses = for {
          charity <- charityDao.getCharities()
        } yield charity.cause

        allCauses.distinct
      }
    }
  }

  def suggestedSkills() = Action {
    Ok {
      Json.toJson {
        val allTags = for {
          charity <- charityDao.getCharities()
          project <- charity.projects
          tag <- project.tags
        } yield tag

        allTags.distinct
      }
    }
  }

  def suggestedQualifications() = Action {
    Ok {

      Json.toJson(List("BA English"))
    }
  }

  case class Volunteer(name: String, email: String, causes: List[String], location: String, skills: List[String],
                       qualifications: List[String], availability: Availability, experienceLevel: String, summary: String)

}

