plugins {
    val kotlinVersion = "1.9.23"
    kotlin("jvm") version kotlinVersion
    id("application")
    id("idea")
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.5")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("org.springframework:spring-core:6.1.6")
    testImplementation(kotlin("test"))

    // datasource
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.5")
    runtimeOnly("com.mysql:mysql-connector-j:8.3.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.3.5")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}