package com.example.clear

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp  // with this annotation hilt can now supply dependencies to any part of app
class ClearApplication : Application() {
}