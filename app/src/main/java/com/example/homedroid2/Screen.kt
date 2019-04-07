package com.example.homedroid2

import android.content.Context
import android.content.Intent
import com.example.homedroid2.Activities.DetailsActivity
import com.example.homedroid2.Activities.MainActivity
import com.example.homedroid2.Activities.ZoomedActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screen {
    class MainScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    class DetailsScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent = Intent(context, DetailsActivity::class.java)
    }

    class ZoomScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent = Intent(context, ZoomedActivity::class.java)
    }
}
