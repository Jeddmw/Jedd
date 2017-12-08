//Object class to store home and away points for a team
public class TeamData {
	//Variables
	double homePoints;
	double awayPoints;

	//Constructor
	public TeamData(double home, double away) {
		this.homePoints = home;
		this.awayPoints = away;
	}

	//toString is called whenever TeamData is being printed out
	public String toString() {
		return "Home points: " + homePoints + "\nAway points: " + awayPoints;
	}
}
