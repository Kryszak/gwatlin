group = "io.github.kryszak"
version = "3.5.0"

plugins {
    val kotlinVersion = "2.1.10"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    kotlin("jvm") version kotlinVersion
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
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

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val kotestVersion = "5.9.1"
        // testing
        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
    }
}

