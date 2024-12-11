import org.jetbrains.dokka.gradle.DokkaTask
import java.util.*

plugins {
    val kotlinVersion = "2.1.0"
    groovy
    jacoco
    signing
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.9.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

project.group = "io.github.kryszak"
project.version = "2.1.3"

internal object Meta {
    const val DESCRIPTION = "Guild Wars 2 API client"
    const val LICENSE = "MiT"
    const val GITHUB_REPOSITORY = "Kryszak/gwatlin"
    const val RELEASE = "https://s01.oss.sonatype.org/service/local/"
    const val SNAPSHOT = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

jacoco {
    toolVersion = "0.8.9"
}

tasks {
    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            html.required.set(false)
        }
    }
    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }
    withType<DokkaTask>().configureEach {
        dokkaSourceSets {
            named("main") {
                moduleName.set("gwatlin")
                includes.from("Module.md")
                perPackageOption {
                    matchingRegex.set(".*api.*")
                    suppress.set(false)
                }
            }
            configureEach {
                perPackageOption {
                    matchingRegex.set(".*")
                    suppress.set(true)
                }
            }
        }
    }
}

signing {
    val signingKey = providers
        .environmentVariable("GPG_SIGNING_KEY_BASE64")
    val signingPassphrase = providers
        .environmentVariable("GPG_SIGNING_PASSPHRASE")

    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(String(Base64.getDecoder().decode(signingKey.get())), signingPassphrase.get())
        val extension = extensions
            .getByName("publishing") as PublishingExtension
        sign(extension.publications)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set(project.name)
                description.set(Meta.DESCRIPTION)
                url.set("https://github.com/${Meta.GITHUB_REPOSITORY}")
                licenses {
                    license {
                        name.set(Meta.LICENSE)
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("kryszak")
                        name.set("Krzysztof Prajs")
                    }
                }
                scm {
                    url.set(
                        "https://github.com/${Meta.GITHUB_REPOSITORY}.git"
                    )
                    connection.set(
                        "scm:git:git://github.com/${Meta.GITHUB_REPOSITORY}.git"
                    )
                    developerConnection.set(
                        "scm:git:git://github.com/${Meta.GITHUB_REPOSITORY}.git"
                    )
                }
                issueManagement {
                    url.set("https://github.com/${Meta.GITHUB_REPOSITORY}/issues")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri(Meta.RELEASE))
            snapshotRepositoryUrl.set(uri(Meta.SNAPSHOT))
            val ossrhUsername = providers
                .environmentVariable("OSSRH_USERNAME")
            val ossrhPassword = providers
                .environmentVariable("OSSRH_PASSWORD")
            if (ossrhUsername.isPresent && ossrhPassword.isPresent) {
                username.set(ossrhUsername.get())
                password.set(ossrhPassword.get())
            }
        }
    }
}

repositories {
    mavenCentral()
}

val fuelVersion = "2.3.1"
val kotlinxSerializationVersion = "1.7.3"
val loggingVersion = "3.0.5"
val logbackVersion = "1.5.12"
val kotestVersion = "5.9.1"
val kotestWiremockExtensionVersion = "3.1.0"
val kotlinWiremockDslVersion = "2.1.1"

dependencies {
    // http
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("com.github.kittinunf.fuel:fuel-kotlinx-serialization:$fuelVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:$loggingVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    // testing
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-wiremock:$kotestWiremockExtensionVersion")
    testImplementation("com.marcinziolo:kotlin-wiremock:$kotlinWiremockDslVersion")
}
