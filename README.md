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
Utah            98.92   vs	Houston         102.81
Brooklyn        107.72  vs	Okla City       109.92
Phoenix         101.73	vs	Washington      113.93
Philadelphia    112.18	vs	LA Lakers       104.11

```

This project demonstrates a profeciency in 2D Arrays(getSchedule method), HashMaps(getData method), and Objects(new Object TeamData) in Java.
