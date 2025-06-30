import java.util.*

group = rootProject.group
version = rootProject.version

plugins {
    jacoco
    signing
    id("maven-publish")
    id("org.jetbrains.dokka") version "2.0.0"
    kotlin("plugin.serialization") version "2.1.21"
}

internal object Meta {
    const val DESCRIPTION = "Guild Wars 2 API client"
    const val LICENSE = "MiT"
    const val LICENSE_URL = "https://opensource.org/license/MIT"
    const val GITHUB_REPOSITORY = "Kryszak/gwatlin"
    const val DEVELOPER = "kryszak"
    const val DEVELOPER_NAME = "Krzysztof Prajs"
}

java {
    withSourcesJar()
    withJavadocJar()
}

jacoco {
    toolVersion = "0.8.12"
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
        finalizedBy(jacocoTestReport)
    }
}

dokka {
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
            artifactId = rootProject.name
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
                        url.set(Meta.LICENSE_URL)
                    }
                }
                developers {
                    developer {
                        id.set(Meta.DEVELOPER)
                        name.set(Meta.DEVELOPER_NAME)
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

val fuelVersion = "2.3.1"
val kotlinxSerializationVersion = "1.9.0"
val loggingVersion = "3.0.5"
val logbackVersion = "1.5.18"
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
    testImplementation("io.kotest.extensions:kotest-extensions-wiremock:$kotestWiremockExtensionVersion")
    testImplementation("com.marcinziolo:kotlin-wiremock:$kotlinWiremockDslVersion")
}
