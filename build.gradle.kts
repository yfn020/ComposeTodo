import io.gitlab.arturbosch.detekt.*

plugins {
    kotlin("multiplatform") version "1.7.20" apply false
    kotlin("plugin.serialization") version "1.7.20" apply false
    id("org.jetbrains.compose") version "1.2.1" apply false
    id("app.cash.sqldelight") version "2.0.0-alpha04" apply false

    id("com.android.application") version "7.3.1" apply false
    id("com.google.cloud.tools.jib") version "3.3.1" apply false

    id("app.cash.licensee") version "1.6.0" apply false
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

buildscript {
    dependencies {
        classpath("org.apache.commons:commons-compress:1.22")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()

        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

detekt {
    source = files(rootProject.rootDir)
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
}

tasks {
    fun SourceTask.config() {
        include("**/*.kt")
        exclude("**/*.kts")
        exclude("**/resources/**")
        exclude("**/generated/**")
        exclude("**/build/**")
    }
    withType<DetektCreateBaselineTask>().configureEach {
        config()
    }
    withType<Detekt>().configureEach {
        config()

        reports {
            sarif.required.set(true)
        }
    }
}
