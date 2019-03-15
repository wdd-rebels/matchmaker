package controllers

import javax.inject._
import play.api._
import play.api.libs.json._
import play.api.mvc._

import scala.collection.immutable

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

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

  case class Charity(name: String, email: String, website: String, cause: String, location: String, tags: List[String], projects: List[Project])

  case class Availability(`type`: String)

  case class Project(title: String, description: String, availability: Availability, tags: List[String])

  object Project {
    implicit val oformat = Json.format[Project]
  }

  val allProjects: List[Project] = List(
//    Project("test1", "description"),
//    Project("test2", "description")
  )

  def projects() = Action{ implicit req =>
    Ok(
      Json.toJson[List[Project]](
        allProjects
      )
    )
  }

  case class Volunteer(name: String, email: String, causes: List[String], location: String, skills: List[String],
                       qualifications: List[String], availability: Availability, experienceLevel: String, summary: String)
}

