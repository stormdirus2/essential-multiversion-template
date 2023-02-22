import gg.essential.gradle.util.*

plugins {
    kotlin("jvm") version "1.6.10" apply false
    // This marks the current project as the root of a multi-version project.
    // Any project using `gg.essential.multi-version` must have a parent with this root plugin applied.
    // Advanced users may use multiple (potentially independent) multi-version trees in different sub-projects.
    // This is currently equivalent to applying `com.replaymod.preprocess-root`.
    id("gg.essential.multi-version.root")
}

version = versionFromBuildIdAndBranch()

preprocess {
    // Here you first need to create a node per version you support and assign it an integer Minecraft version.
    // The mappings value is currently meaningless.
    val forge11903 = createNode("1.19.3-forge", 11903, "srg")
    val fabric11903 = createNode("1.19.3-fabric", 11903, "yarn")
    val forge11902 = createNode("1.19.2-forge", 11902, "srg")
    val fabric11902 = createNode("1.19.2-fabric", 11902, "yarn")
    val forge11801 = createNode("1.18.1-forge", 11801, "srg")
    val fabric11801 = createNode("1.18.1-fabric", 11801, "yarn")
    val forge11701 = createNode("1.17.1-forge", 11701, "srg")
    val fabric11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val fabric11602 = createNode("1.16.2-fabric", 11602, "yarn")
    val forge11602 = createNode("1.16.2-forge", 11602, "srg")
    val forge11202 = createNode("1.12.2-forge", 11202, "srg")
    val forge10809 = createNode("1.8.9-forge", 10809, "srg")

    
    // And then you need to tell the preprocessor which versions it should directly convert between.
    // This should form a directed graph with no cycles (i.e. a tree), which the preprocessor will then traverse to
    // produce source code for all versions from the main version.
    // Do note that the preprocessor can only convert between two projects when they are either on the same Minecraft
    // version (but use different mappings, e.g. 1.16.2 forge to fabric), or when they are using the same intermediary
    // mappings (but on different Minecraft versions, e.g. 1.12.2 forge to 1.8.9 forge, or 1.16.2 fabric to 1.18 fabric)
    // but not both at the same time, i.e. you cannot go straight from 1.12.2 forge to 1.16.2 fabric, you need to go via
    // an intermediary 1.16.2 forge project which has something in common with both.
    fabric11903.link(fabric11902)
    fabric11902.link(fabric11801)
    fabric11801.link(fabric11701)
    fabric11701.link(fabric11602)

    forge11903.link(fabric11903)

    forge11903.link(forge11902)
    forge11902.link(forge11801)
    forge11801.link(forge11701)
    forge11701.link(forge11602)
    forge11602.link(forge11202)
    forge11202.link(forge10809)
    // For any link, you can optionally specify a file containing extra mappings which the preprocessor cannot infer by
    // itself, e.g. forge intermediary names do not contain class names, so you may need to supply mappings for those
    // manually.
}
