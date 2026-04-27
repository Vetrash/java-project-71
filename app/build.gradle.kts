plugins {
    //id("java")
    id("com.github.ben-manes.versions") version "0.53.0"
    application
    checkstyle
    id ("org.sonarqube") version "7.2.3.7755"

}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

application { mainClass.set("hexlet.code.App") }

sonar {
    properties {
        property("sonar.projectKey", "Vetrash_java-project-71")
        property("sonar.organization", "vetrash")
        property("sonar.coverage.skip", "true")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}