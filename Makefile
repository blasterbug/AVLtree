all: jar

jar:
	@if [ ! -d ./bin ] ; then mkdir ./bin ; fi;
	find ./src -name "*.java" > source_list
	javac -d ./bin @source_list
	cd ./bin && jar cfm ../Bazar.jar ../src/MANIFEST.MF *

doc:
	@if [ ! -d ./doc ] ; then mkdir ./doc ; fi;
	javadoc -public -splitindex -author -version -charset UTF-8 -d ./doc/javadoc bazar parser tree -sourcepath ./src

clean:
	-rm -r ./bin
	-rm Bazar.jar
	-rm source_list

