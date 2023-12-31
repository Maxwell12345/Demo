plugins {
    kotlin("multiplatform") version "1.9.10"

    application
}

group = "mil.ship"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "20"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:2.2.1")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.1")
                implementation("org.jetbrains.kotlinx:kotlin-deeplearning-tensorflow:0.6.0-alpha-1")
                implementation("org.checkerframework:checker:3.38.0")
                implementation("org.openpnp:opencv:4.7.0-0")

            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.467")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.467")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.10.5-pre.467")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("mil.ship.detection.ServerKt")
}



tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}
