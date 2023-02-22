import gg.essential.gradle.util.*

plugins {
    kotlin("jvm")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
    id("gg.essential.defaults.maven-publish")
    id("org.cadixdev.licenser").version("0.6.1")
}

group = "net.author"

java.withSourcesJar()
tasks.compileKotlin.setJvmDefault(if (platform.mcVersion >= 11400) "all" else "all-compatibility")
loom.noServerRunConfigs()

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
    compileOnly("org.spongepowered:mixin:0.8.5")

    if ("$platform" == "1.12.2-forge" || "$platform" == "1.8.9-forge") {
        implementation("gg.essential:loader-launchwrapper:1.1.3")
    }
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.name}" }
    }
    manifest {
        attributes(mapOf(
            "FMLModType" to "MOD",
            "TweakClass" to "gg.essential.loader.stage0.EssentialSetupTweaker",
            "TweakOrder" to "0",
            "MixinConfigs" to "mixins.examplemod.json"
        ))

    }
}

loom {
    // Mixin on forge? (for legacy forge you will still need to register a tweaker to set up mixin)

    if (loom.isForge) {
        launchConfigs.named("client") {
            arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
            // And maybe a core mod?
            //property("fml.coreMods.load", "com.example.asm.CoreMod")
        }

        forge {
            mixinConfig("mixins.examplemod.json")
            // And maybe an access transformer?
            // Though try to avoid these, cause they are not automatically translated to Fabric's access widener
            //accessTransformer(project.parent.file("src/main/resources/example_at.cfg"))
        }
    }
}

license {

    include("**/*.java")

    header.set(rootProject.resources.text.fromFile(rootProject.file("LICENSE_HEADER")))

}