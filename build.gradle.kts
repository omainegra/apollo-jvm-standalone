plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-api-jvm:3.6.2")
    implementation("com.apollographql.apollo3:apollo-runtime-jvm:3.6.2")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okio:okio-jvm:3.2.0")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("apollographql-standalone")

        dependencies {
            val keepModules = setOf(
                "com.meta.kmp",
                "com.benasher44",
                "com.squareup.okio",
                "com.squareup.okhttp3",
                "com.apollographql.apollo3",
            )

            exclude { it.moduleGroup !in keepModules }

            relocate("okio", "okio3")
            relocate("okhttp3", "okhttp4")
        }

        exclude("shadow.okhttp3.internal.publicsuffix.NOTICE")
    }
}