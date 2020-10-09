default:
	javac -cp ./jsoup-1.13.1.jar *.java
	echo '\n' >> README.md
	echo `date` >> README.md
	echo \`\`\` >> README.md
	java -cp ./jsoup-1.13.1.jar:. NBAPredictions >> README.md
	echo \`\`\` >> README.md

clean:
	$(RM) *.class
