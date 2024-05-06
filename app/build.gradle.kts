plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.zhgs.lawrecorder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhgs.lawrecorder"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    signingConfigs {
        // 创建一个名为 "release" 的签名配置
        create("release") {
            // 设置签名所需的存储文件和密码
            storeFile=file("E:\\workspace\\LawRecorder\\app\\sign.jks")
            storePassword="443369"
            keyAlias="key0"
            keyPassword="443369"
        }
    }
    buildTypes{
        // 配置 "release" 构建类型使用上面定义的签名配置
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    buildFeatures {
        viewBinding=true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.zhgs.baseproject:baseproject:1.0.3")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation(files("libs/aicds_001.aar"))
    implementation(files("libs/lib-release.aar"))
}