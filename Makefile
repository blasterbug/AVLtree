all: jar

jar:
	-mkdir ./bin
	find ./src -name "*.java" > source_list
	javac -d ./bin @source_list
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

doc:
	@-mkdir ./doc
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

clean:
	-rm -r ./bin
	-rm Bazar.jar
	-rm source_list

