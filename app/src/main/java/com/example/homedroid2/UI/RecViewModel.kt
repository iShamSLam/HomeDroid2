package com.example.homedroid2.UI

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.homedroid2.Components.MealAdapter
import com.example.homedroid2.R
import com.example.homedroid2.model.Api.FoodApiService
import com.example.homedroid2.model.Meals.Meal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class JobsListViewModel : BaseViewModel() {

    @Inject
    lateinit var mealsApi: FoodApiService

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadJobs() }
    val jobListAdapter = MealAdapter()

    init {
        loadJobs()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadJobs() {
        subscription = mealsApi.getJobs(appId, appKey, what, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveJobsListStart() }
            .doAfterTerminate { onRetrieveJobsListFinish() }
            .subscribe({ result ->
                onRetrieveJobsListSuccess(result.results)
            }, {
                onRetrieveJobsListError()
            })
    }

    private fun onRetrieveJobsListError() {
        errorMessage.value = R.string.job_error
    }

    private fun onRetrieveJobsListSuccess(jobList: List<Result>) {
        jobListAdapter.updateJobList(jobList)
    }

    private fun onRetrieveJobsListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveJobsListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

}