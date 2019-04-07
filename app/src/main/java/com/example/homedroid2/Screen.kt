package com.example.homedroid2

import android.content.Context
import android.content.Intent
import com.example.homedroid2.Activities.DetailsActivity
import com.example.homedroid2.Activities.MainActivity
import com.example.homedroid2.Activities.ZoomedActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screen {
    public final class MainScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    public final class DetailsScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, DetailsActivity::class.java)
        }
    }

    public final class ZoomScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, ZoomedActivity::class.java)
        }
    }
}
