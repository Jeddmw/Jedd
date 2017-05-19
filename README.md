# Simple NBA Predictions

Uses method described [here](http://www.basketbet.net/how-to-start-betting-online/basketball-betting-types/totals-bets/using-home-away-averages-to-predict-nba-games/) to predict NBA games.

Data is extracted from teamrankings.com.

In order to run this program download the [jsoup library](https://jsoup.org/) and add it to the classpath.

To compile and run from command line (change jsoup file name/location if necessary):
```
javac -cp ./jsoup-1.8.3.jar NBAPredictions.java
java -cp ./jsoup-1.8.3.jar:. NBAPredictions
```

Example output for 3/6/2017 games:
```
Home team:			Away team:
Atlanta: 102.51073361956318	Boston: 107.632841269333
Indiana: 104.36102296061229	Milwaukee: 100.33750680457268
New York: 107.3085682284861	Washington: 110.38624355566941
Portland: 113.03229419451188	Minnesota: 107.89970860418202
Philadelphia: 100.46201232032854	Chicago: 103.86105863140031
Orlando: 106.66268433824902	Brooklyn: 106.96115789810753
```

This project demonstrates a profeciency in 2D Arrays(getSchedule method), HashMaps(getData method), and Objects(new Object TeamData) in Java.
