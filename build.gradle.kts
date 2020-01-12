plugins {
    kotlin("multiplatform") version "1.3.60"
    kotlin("plugin.serialization") version "1.3.60"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
}

repositories {
    mavenCentral()
    maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    maven { setUrl("https://dl.bintray.com/kotlin/ktor") }
    maven { setUrl("https://dl.bintray.com/jetbrains/kotlin-native-dependencies") }
}

val ktorVersion = "1.3.0-beta-2"
val serializationVersion = "0.14.0-1.3.60-eap-76"

kotlin {
    linuxX64("native") {
        binaries {
            executable {
                baseName = "swapi-cli"
                entryPoint = "com.github.rodolphocouto.main"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }
    }
}
