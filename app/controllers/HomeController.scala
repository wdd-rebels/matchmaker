package controllers

import dao.CharityDao
import javax.inject._
import model.{Availability, Project}
import play.api.libs.json._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, projectDao: CharityDao) extends AbstractController(cc) {

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

  def projects() = Action { implicit req =>
    Ok(
      Json.toJson[List[Project]](
        //        projectDao.getCharities()
        List(Project("test", "description", Availability("blah"), Nil))
      )
    )
  }

  def suggestedCauses() = Action {
    Ok {
      Json.toJson(List(
        "Animal", "Environmental", "Arts & Culture"
      ))
    }
  }

  def suggestedSkills() = Action {
    Ok {

      Json.toJson(List("html", "css", "node.js"))
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

