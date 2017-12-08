# Simple NBA Predictions

Uses method described [here](http://www.basketbet.net/how-to-start-betting-online/basketball-betting-types/totals-bets/using-home-away-averages-to-predict-nba-games/) to predict NBA games.

Data is extracted from teamrankings.com.

In order to run this program download the [jsoup library](https://jsoup.org/) and add it to the classpath.

To compile and run from command line (change jsoup file name/location if necessary):
```
javac -cp ./jsoup-1.8.3.jar *.java
java -cp ./jsoup-1.8.3.jar:. NBAPredictions
```

Example output for 12/7/2017 games:
```
Home team:			Away team:
Utah: 98.91671100816927	Houston: 102.81344192907075
Brooklyn: 107.72478011831357	Okla City: 109.9169178477522
Phoenix: 101.72684591067014	Washington: 113.93372025909062
Philadelphia: 112.17628094776049	LA Lakers: 104.11017764381452
```

This project demonstrates a profeciency in 2D Arrays(getSchedule method), HashMaps(getData method), and Objects(new Object TeamData) in Java.
