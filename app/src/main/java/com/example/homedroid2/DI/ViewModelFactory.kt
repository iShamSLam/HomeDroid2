package com.example.homedroid2.DI

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import shgbievi.gmail.com.jobsapp.ui.jobs.JobViewModel
import shgbievi.gmail.com.jobsapp.ui.jobsList.JobsListViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobsListViewModel::class.java)) {
            return JobsListViewModel() as T
        }
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            return JobViewModel() as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }

}