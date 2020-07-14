default:
	javac -cp ./jsoup-1.13.1.jar *.java
	java -cp ./jsoup-1.13.1.jar:. NBAPredictions

clean:
	$(RM) *.class
