// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
//    alias(libs.plugins.vkompose) apply false
//    alias(libs.plugins.vkompose.logger) apply false
//    alias(libs.plugins.vkompose.highlighter) apply false
//    alias(libs.plugins.vkompose.skip.checker) apply false
//    alias(libs.plugins.vkompose.source.cleaner) apply false
}
