package com.example.homedroid2.UI

import android.app.ActivityOptions
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.TextView
import com.example.homedroid2.Activities.DescActivity
import com.example.homedroid2.Components.getParentActivity
import com.example.homedroid2.model.Meals.Meal

class JobViewModel : BaseViewModel() {

    private val jobTitle = MutableLiveData<String>()
    private val jobBody = MutableLiveData<String>()


    fun getJobTitle() = jobTitle

    fun getJobBody() = jobBody

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startDescActivity(view: View?, title: String, body: String) {
        var intent = Intent(view?.getParentActivity(), DescActivity::class.java)
        var titleTv = TextView(view?.getParentActivity())
        titleTv.text = title
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(view?.getParentActivity(), titleTv, "details")
        intent.putExtra("body", body)
        intent.putExtra("title", title)
        view?.getParentActivity()?.startActivity(intent, transitionActivityOptions.toBundle())
    }

}