plugins {
    kotlin("multiplatform") version "1.3.70-eap-42"
    kotlin("plugin.serialization") version "1.3.70-eap-42"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
}

repositories {
    mavenCentral()
    maven { url = uri("https://kotlin.bintray.com/kotlin-dev") }
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
}

val ktorVersion = "1.3.0-rc3-1.3.70-eap-42"
val kotlinxCoroutinesVersion = "1.3.3-1.3.70-eap-42"
val kotlinxSerializationVersion = "0.14.0-1.3.70-eap-42"

kotlin {
    linuxX64("native") {
        compilations["main"].enableEndorsedLibs = true
        binaries {
            executable {
                baseName = "swapi-cli"
                entryPoint = "com.github.rodolphocouto.swapi.cli.main"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$kotlinxSerializationVersion")
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
                implementation("io.ktor:ktor-client-core-native:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization-native:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinxCoroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$kotlinxSerializationVersion")
            }
        }
    }
}
