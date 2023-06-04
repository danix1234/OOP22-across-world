// ".\gradlew.bat" instead of "./gradlew" if you are on windows

plugins { 
	 // Apply the java plugin to add support for Java
    java

	// Apply the application plugin to add support for building a CLI application
	// run application via "./gradlew run" 
    application

    // add task to create a runnable jar
	// create runnable jar via "./gradlew shadowJar"
	// the runnable jar will be found in "build/lib/<name>.jar"
	id("com.github.johnrengelman.shadow") version "8.1.1"

	// support for code quality checkers
	// check code quality via "./gradlew check"
	id("org.danilopianini.gradle-java-qa") version "1.6.0"
    
	// support for java version
	id("java-library")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
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
	val jUnitVersion = "5.9.2"
	testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")	
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}