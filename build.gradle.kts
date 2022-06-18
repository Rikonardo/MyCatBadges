plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.rikonardo.mycatbadges"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.rikonardo.com/releases")
    }
}

dependencies {
    compileOnly("com.rikonardo.catbadge:badge-api:1.0.0")
    implementation("dev.virefire.kson:KSON:1.3.1")
    implementation("dev.virefire.yok:Yok:1.0.4")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

tasks.build {
    dependsOn(tasks.getByName("shadowJar"))
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveClassifier.set("")
}
