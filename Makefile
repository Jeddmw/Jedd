default:
	javac -cp ./jsoup-1.13.1.jar *.java
	echo '\n' >> scores.txt
	echo `date` >> scores.txt
	java -cp ./jsoup-1.13.1.jar:. NBAPredictions >> scores.txt

clean:
	$(RM) *.class
