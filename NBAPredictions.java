import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Predicts NBA games using basic math. Uses Jsoup
public class NBAPredictions {
    public static void main(String args[]) throws IOException {
        //Get points scored and conceded home and away by team
        HashMap < String, TeamData > pointsScored = getData(true);
        HashMap < String, TeamData > pointsConceded = getData(false);

        //Calculate average home/away points scored
        double homeSum = 0;
        double awaySum = 0;
        for (String teamName: pointsScored.keySet()) {
            TeamData data = pointsScored.get(teamName);
            double homePoints = data.homePoints;
            double awayPoints = data.awayPoints;
            homeSum = homeSum + homePoints;
            awaySum = awaySum + awayPoints;
        }
        double homeAvg = homeSum / 30;
        double awayAvg = awaySum / 30;

        System.out.println("Home team:\t\t\tAway team:"); //Print Header

        //Get schedule and loop through schedule and use data to calculate projected points
        String[][] schedule = getSchedule();
        for (String[] game: schedule) {
            //Get team names
            String homeTeam = game[1];
            String awayTeam = game[0];

            try {
                //Get both team's points scored and conceded values from Hashmap
                double homePointsScored = pointsScored.get(homeTeam).homePoints;
                double awayPointsScored = pointsScored.get(awayTeam).awayPoints;
                double homePointsConceded = pointsConceded.get(homeTeam).homePoints;
                double awayPointsConceded = pointsConceded.get(awayTeam).awayPoints;

                //Calculate home and away offensive and defensive factors
                //Ex: LA Lakers concede an average of 113.9	points away, compared
                // to the league average of 107.14. Therefore, compared to the average NBA defense,
                // LA concedes ~6.3% more points on road trips than the average NBA defense (2016-2017 season as of 3/6/2017)
                double homeOffFactor = homePointsScored / homeAvg;
                double homeDefFactor = homePointsConceded / awayAvg; //away points scored avg is home points conceded avg
                double awayOffFactor = awayPointsScored / awayAvg;
                double awayDefFactor = awayPointsConceded / homeAvg; //home points scored avg is away points conceded avg

                //Multiply the average points scored home/away by each of the offensive and defensive factors
                double homePoints = homeAvg * homeOffFactor * awayDefFactor;
                double awayPoints = awayAvg * awayOffFactor * homeDefFactor;

                //Print out projections
                System.out.format("%-16s%-5.2f\tvs\t%-16s%-5.2f\n", (homeTeam + ":"), homePoints, (awayTeam + ":"), awayPoints);
            } catch (NullPointerException ex) {
                //Could not find team name
            }
        }
    }

    //gets Data either points scored or conceded
    static HashMap < String, TeamData > getData(boolean isOffensive) throws IOException {
        HashMap < String, TeamData > data = new HashMap < String, TeamData > ();
        String url = "https://www.teamrankings.com/nba/stat/points-per-game";
        if (!isOffensive) {
            url = "https://www.teamrankings.com/nba/stat/opponent-points-per-game";
        }
        Document doc = Jsoup.connect(url).timeout(10 * 1000).get(); // timeout = 10 seconds
        Element table = doc.select("table[class=tr-table datatable scrollable]").first(); //select table
        for (Element row: table.select("tr")) {
            Elements tds = row.select("td");
            if (tds.size() > 1) {
                String team = tds.get(1).text();
                double homePoints = Double.parseDouble(tds.get(5).text());
                double awayPoints = Double.parseDouble(tds.get(6).text());
                TeamData points = new TeamData(homePoints, awayPoints);
                data.put(team, points); //Store data in Hashmap
            }
        }
        return data;
    }

    //Index 0 is away 1 is home
    static String[][] getSchedule() throws IOException {
        String url = "https://www.teamrankings.com/nba/schedules/";
        Document doc = Jsoup.connect(url).timeout(10 * 1000).get(); // timeout = 10 seconds
        Element table = doc.select("table[class=tr-table datatable scrollable]").first(); //select table
        Elements rows = table.select("tr");
        String[][] data = new String[rows.size() - 1][2];
        int count = 0; //which row we're on. will use to store data in 2D Array
        for (Element row: rows) {
            Elements tds = row.select("td");
            if (tds.size() > 1) {
                //Original String looks something like "#28 LA Lakers at #1 Golden State"
                //first remove number and #
                String temp = tds.get(2).text().replaceAll("\\d", "").replaceAll("#", "").replaceAll("\\.", "").trim();
                //all that's left is to split the string by at to get home and away teams
                String[] split = {
                    "",
                    ""
                };
                if (temp.contains("at")) {
                    split = temp.split("at", 2); //only split on first instance
                } else if (temp.contains("vs")) {
                    split = temp.split("vs", 2); //only split on first instance
                }
                split[0] = split[0].trim(); //remove trailing whitespace
                split[1] = split[1].trim(); //remove trailing whitespace
                data[count] = split; //store data in 2D String Array
                count++;
            }
        }
        return data;
    }
}
