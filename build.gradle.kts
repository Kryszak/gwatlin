plugins {
    groovy
    jacoco
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.8.10"
    kotlin("jvm") version "1.8.21"
}

val fuelVersion = "2.3.1"
val gsonVersion = "2.10.1"
val loggingVersion = "3.0.5"
val logbackVersion = "1.4.7"
val spockVersion = "2.3-groovy-4.0"
val groovyVersion = "4.0.11"
val wiremockVersion = "2.35.0"
val kotestVersion = "5.6.1"
val kotestWiremockExtensionVersion = "2.0.0"
val kotlinWiremockDslVersion = "2.0.0"

project.group = "com.kryszak"
project.version = "1.6"

sourceSets {
    getByName("main").apply {
        kotlin.srcDirs("src/main/kotlin")
    }
    getByName("test").apply {
        kotlin.srcDirs("src/test/kotlin")
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

tasks.build {
    finalizedBy(tasks.dokkaHtml)
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
    implementation("com.github.kittinunf.fuel:fuel-gson:$fuelVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:$loggingVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // testing
    testImplementation("org.spockframework:spock-core:$spockVersion")
    testImplementation("org.apache.groovy:groovy-all:$groovyVersion")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-wiremock:$kotestWiremockExtensionVersion")
    testImplementation("com.marcinziolo:kotlin-wiremock:$kotlinWiremockDslVersion")
}
