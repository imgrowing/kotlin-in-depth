import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
}

group = "my.ex"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // kotest
    val kotestVersion = "5.5.4"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")         // kotest framework
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")       // assertions
    testImplementation("io.kotest:kotest-property:$kotestVersion")              // property testing
    testImplementation("io.kotest:kotest-framework-datatest:${kotestVersion}")
    // https://mockk.io/
    testImplementation("io.mockk:mockk:1.13.3") { // mocking
        exclude(group = "junit", module = "junit")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
