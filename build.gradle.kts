plugins { 
	// support for java
    java

    // support for running application on the terminal
	// ".\gradlew.bat" instead of "./gradlew" if you are on windows
	// run application via "./gradlew run" 
    application

    // add task to create a runnable jar
	// create runnable jar via "./gradlew shadowJar"
	// the runnable jar will be found in "build/lib/<name>.jar"
	id("com.github.johnrengelman.shadow") version "7.0.0"

	// support for code quality checkers
	// check code quality via "./gradlew check"
	id("org.danilopianini.gradle-java-qa") version "0.41.0"
}

application{
    mainClass.set("it.unibo.project.App")
}

repositories {
	mavenCentral()
}

dependencies {
	// suppression for SpotBugs
	compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")

	// support for Junit test library
	// test code via "./gradlew test"
	val jUnitVersion = "5.9.1"
	testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")	
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()	
}
