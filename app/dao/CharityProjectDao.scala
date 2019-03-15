package dao

import model.CharityProject
import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer
import scala.io.Source

class CharityProjectDao {

  private val charities: ListBuffer[CharityProject] = {
    val projectsRaw = Source.fromResource("charities.json").mkString
    val projectsJson = Json.parse(projectsRaw)
    projectsJson.validate[ListBuffer[CharityProject]].get
  }

  def getCharities(): List[CharityProject] = {
    charities.toList
  }

  def add(charity: CharityProject): Unit = {
    charities.append(charity)
  }

  def getSuggestedSkills(): List[String] = {
    val allTags = for {
      charity <- getCharities()
      skill <- charity.skills
    } yield skill

    allTags.distinct
  }

  def getSuggestedCauses(): List[String] = {
    val allCauses = for {
      charity <- getCharities()
      cause <- charity.causes
    } yield cause

    allCauses.distinct
  }
}
