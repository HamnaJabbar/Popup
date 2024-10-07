plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.24"
  id("org.jetbrains.intellij") version "1.17.3"
}

group = "org.intellij.sdk"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

intellij {
  version.set("2023.2.6")
  type.set("IC")
}


java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("232")
    untilBuild.set("242.*")
  }
}
