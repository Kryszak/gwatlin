plugins {
    groovy
    jacoco
    id("maven-publish")
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
}

val fuelVersion = "2.3.1"
val kotlinxSerializationVersion = "1.5.0"
val loggingVersion = "3.0.5"
val logbackVersion = "1.4.6"
val spockVersion = "2.3-groovy-4.0"
val groovyVersion = "4.0.11"
val wiremockVersion = "2.35.0"

project.group = "com.kryszak"
project.version = "1.6"

sourceSets {
    getByName("main").apply {
        kotlin.srcDirs("src/main/kotlin")
    }
    getByName("test").apply {
        groovy.srcDirs("src/test/groovy")
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

java {
    withJavadocJar()
}

jacoco {
    toolVersion = "0.8.9"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(false)
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/kryszak/gwatlin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // http
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("com.github.kittinunf.fuel:fuel-kotlinx-serialization:$fuelVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:$loggingVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // testing
    testImplementation("org.spockframework:spock-core:$spockVersion")
    testImplementation("org.apache.groovy:groovy-all:$groovyVersion")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")
}
