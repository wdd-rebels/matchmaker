package dao

import model.{Charity, Project}
import play.api.libs.json.Json

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

class CharityDao {

  private val charities: ListBuffer[Charity] = {
    val projectsRaw = Source.fromResource("charities.json").mkString
    val projectsJson = Json.parse(projectsRaw)
    projectsJson.validate[ListBuffer[Charity]].get
  }

  def getCharities(): List[Charity] = {
    charities.toList
  }

  def add(charity: Charity): Unit = {
    charities.append(charity)
  }


  def getSuggestedSkills(): List[String] = {
    val allTags = for {
      charity <- getCharities()
      project <- charity.projects
      tag <- project.tags
    } yield tag

    allTags.distinct
  }

  def getSuggestedCauses(): List[String] = {
    val allCauses = for {
      charity <- getCharities()
    } yield charity.cause

    allCauses.distinct
  }
}
