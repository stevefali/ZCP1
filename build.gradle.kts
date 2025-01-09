plugins {
    kotlin("jvm") version "2.0.21"
    id("idea")
}

group = "com.steve"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    mavenLocal()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.3-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.spigotmc:spigot:1.21.3-R0.1-SNAPSHOT")

}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.jar {
    doLast {
        when (val exportPath = project.findProperty("plugin.export")) {
            is String -> {
                copy {
                    from(archiveFile)
                    into(File(exportPath))
                    rename(".*", "${rootProject.name}.jar")
                }
                println("Exported plugin to $exportPath")
            }

            else -> println("You can export the plugin jar to your server plugins folder by providing its path with the 'plugin.export' property of 'gradle.properties'")
        }
    }
}



tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
