plugins {
    groovy
    jacoco
    id("maven-publish")
    kotlin("jvm") version "1.8.20"
}

val kotlin_version = "1.8.20"
val spock_version = "2.3-groovy-4.0"
val groovy_version = "4.0.11"
val fuel_version = "2.3.1"
val wiremock_version = "2.35.0"
val gson_version = "2.10.1"
val slf4j_version = "2.0.7"
val logback_version = "1.4.6"

group = "com.kryszak"
version = "1.5"

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
    implementation("com.github.kittinunf.fuel:fuel:$fuel_version")
    implementation("com.github.kittinunf.fuel:fuel-gson:$fuel_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("com.google.code.gson:gson:$gson_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("ch.qos.logback:logback-core:$logback_version")
    testImplementation("org.spockframework:spock-core:$spock_version")
    testImplementation("org.apache.groovy:groovy-all:$groovy_version")
    testImplementation("com.github.tomakehurst:wiremock-jre8:$wiremock_version")
}
