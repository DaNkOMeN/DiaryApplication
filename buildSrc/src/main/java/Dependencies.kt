object Dependencies {


    object Compose {
        const val version = "1.0.1"
        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"

        //for animation navigation
        const val accompanist = "com.google.accompanist:accompanist-navigation-animation:0.16.0"

        //compose navigation
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha4"
    }


    //dependency injection
    object Hilt {
        const val hiltCompose = "1.0.0-alpha03"
        const val hiltLifecycleViewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltCompose"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltCompose"

        const val hilt = "2.37"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hilt"
        const val hiltAndroid = "com.google.dagger:hilt-android:$hilt"

        const val hiltComp = "1.0.0"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:$hiltComp"

    }

    //room database
    object Room {
        const val room = "2.3.0"
        const val roomRuntime = "androidx.room:room-runtime:$room"
        const val roomCompiler = "androidx.room:room-compiler:$room"
        const val roomKtx = "androidx.room:room-ktx:$room"
    }


    //json serialization
    object Gson {
        const val gson = "com.google.code.gson:gson:2.8.6"
    }

}