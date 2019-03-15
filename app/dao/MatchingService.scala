package dao

import javax.inject.Inject
import model.{CharityProject, Volunteer}

class MatchingService @Inject()(charityProjectDao: CharityProjectDao) {
  def matchingCharities(volunteer: Volunteer): List[CharityProject] = {
    charityProjectDao.getCharities().sortBy(score(volunteer, _))
  }

  private def score(volunteer: Volunteer, project: CharityProject): Double = {
    val availabilityScore = if (volunteer.availability == project.availability) 5 else 0
    val locationScore = if (volunteer.location == project.location) 6 else 0
    val causesScore = volunteer.causes.union(project.causes).length * 1.5
    val skillsScore = volunteer.skills.union(project.skills).length
    availabilityScore+locationScore+causesScore+skillsScore
  }
}
