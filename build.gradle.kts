plugins {
    kotlin("multiplatform") version "1.3.60"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
}

repositories {
    mavenCentral()
}

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
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
