package com.example.homedroid2.di.Component

import com.example.homedroid2.Activities.DetailsActivity
import com.example.homedroid2.Activities.MainActivity
import com.example.homedroid2.Activities.ZoomedActivity
import dagger.Component
import com.example.homedroid2.di.Module.BookModule

@Component(dependencies = [AppComponent::class], modules = [BookModule::class])
interface BookComponent
{
    fun inject (mainActivity: MainActivity)

    fun inject (zoomedActivity: ZoomedActivity)

    fun inject (detailsActivity: DetailsActivity)
}
