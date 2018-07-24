import com.sun.org.glassfish.external.amx.AMXUtil.prop
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.plugins.ExtensionAware
import org.gradle.internal.impldep.org.testng.reporters.XMLUtils.xml
import org.gradle.wrapper.GradleWrapperMain
import org.jetbrains.grammarkit.tasks.*

val kotlinVersion = prop("kotlinVersion")

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven { setUrl("http://dl.bintray.com/jetbrains/intellij-plugin-service") }
        maven { setUrl("https://jitpack.io") }

        dependencies {
            classpath(kotlin("gradle-plugin", extra.properties["kotlinVersion"] as String))
            classpath("com.github.JetBrains:gradle-grammar-kit-plugin:2018.1.2")
        }
    }
}

plugins {
    idea
    id("org.jetbrains.intellij").version("0.3.4")
}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("jacoco")
    plugin("idea")
    plugin("org.jetbrains.grammarkit")
    plugin("org.jetbrains.intellij")
}

java.sourceSets {
    getByName("main").java.srcDirs("src/main/gen")
}

idea {
    module {
        generatedSourceDirs.add(file("gen"))
    }
}

val generateHtlLexer = task<GenerateLexer>("generateHtlLexer") {
    group = "grammar"
    source = "src/main/grammars/htl.flex"
    targetDir = "src/main/gen/co/nums/intellij/aem/htl/lexer"
    targetClass = "HtlLexer"
    purgeOldFiles = true
}

val generateHtlParser = task<GenerateParser>("generateHtlParser") {
    group = "grammar"
    source = "src/main/grammars/htl.bnf"
    targetRoot = "src/main/gen"
    pathToParser = "/co/nums/intellij/aem/htl/parser/HtlParser.java"
    pathToPsiRoot = "/co/nums/intellij/aem/htl/psi"
    purgeOldFiles = true
}

tasks.withType<KotlinCompile> {
    dependsOn(generateHtlLexer, generateHtlParser)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = "1.2"
    }
}

intellij {
    version = prop("ideaVersion")
    updateSinceUntilBuild = false
    sandboxDirectory = project.rootDir.canonicalPath + "/.sandbox"
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
        csv.isEnabled = false
        html.isEnabled = false
    }
}

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
    maven { setUrl("http://dl.bintray.com/jetbrains/intellij-plugin-service") }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:${prop("kotlinVersion")}")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${prop("kotlinVersion")}")
    compile("org.eclipse.mylyn.github:org.eclipse.egit.github.core:${prop("egitGithubVersion")}")

    testCompile("junit:junit:${prop("junit4Version")}")
    testCompile("org.assertj:assertj-core:${prop("assertjVersion")}")
    testCompile("org.mockito:mockito-core:${prop("mockitoVersion")}")
    testCompile("org.jmockit:jmockit:${prop("jMockitVersion")}")

    testCompile("org.junit.jupiter:junit-jupiter-api:${prop("junit5Version")}")
    testCompile("org.junit.jupiter:junit-jupiter-engine:${prop("junit5Version")}")
    testCompile("org.junit.vintage:junit-vintage-engine:${prop("junit5Version")}")
    testCompile("org.junit.jupiter:junit-jupiter-params:${prop("junit5Version")}")

    testCompile("org.junit.platform:junit-platform-launcher:${prop("junit5PlatformVersion")}")
    testCompile("org.junit.platform:junit-platform-console:${prop("junit5PlatformVersion")}")
}

fun prop(name: String) =
        extra.properties[name] as? String
                ?: error("Property `$name` is not defined in gradle.properties")
