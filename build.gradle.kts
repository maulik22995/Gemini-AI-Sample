// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.secrateGradlePlugin) apply  false
    alias(libs.plugins.kaptPlugin) apply  false
    alias(libs.plugins.hiltPlugin) apply  false
}