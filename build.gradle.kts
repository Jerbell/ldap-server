import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.unboundid:unboundid-ldapsdk:4.0.11")
}

tasks.withType<KotlinCompile>().configureEach {
    println("Configuring $name in project ${project.name} to use JVM bytecode 1.8")
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test>().configureEach {
    println("Configuring $name in project ${project.name} to use JUnit 5")
    useJUnitPlatform()
}
