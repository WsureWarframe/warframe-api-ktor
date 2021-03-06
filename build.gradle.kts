val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val brotliVersion = "1.6.0"
val operatingSystem: OperatingSystem = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "top.wsure.top"
version = "0.0.1"
application {
    mainClass.set("top.wsure.top.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    // https://mvnrepository.com/artifact/com.nixxcode.jvmbrotli/jvmbrotli
    implementation("com.aayushatharva.brotli4j:brotli4j:$brotliVersion")
    implementation(
        "com.aayushatharva.brotli4j:native-${
            if (operatingSystem.isWindows) "windows-x86_64"
            else if (operatingSystem.isMacOsX) "osx-x86_64"
            else if (operatingSystem.isLinux)
                if (org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentArchitecture().isArm) "linux-aarch64"
                else "native-linux-x86_64"
            else ""
        }:$brotliVersion"
    )

    implementation("com.github.kittinunf.result:result-jvm:5.2.0")
    implementation("com.github.kittinunf.fuse:fuse:1.2.1")
    implementation("com.esotericsoftware:kryo:5.2.0")

    implementation("com.dropbox.mobile.store:store4:4.0.2-KT15")

    implementation("io.github.reactivecircus.cache4k:cache4k:0.3.0")
    implementation("org.ehcache:ehcache:3.9.7")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}
tasks{
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "top.wsure.top.Application"))
        }
    }
}