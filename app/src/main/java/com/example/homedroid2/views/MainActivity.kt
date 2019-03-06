package com.example.homedroid2.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.homedroid2.DataAdapter
import com.example.homedroid2.R
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.presenter.MainPresenter
import io.apptitude.premiumparking.utils.functions.observableFromSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


class MainActivity : MvpAppCompatActivity(), MainView, DataAdapter.BooksViewHolder.OnBookClick {

    private val TAG = MainView::class.java.simpleName

    private var mBookArrayList: ArrayList<Book>? = null

    private var mAdapter: DataAdapter? = null

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

        val REWRITE_CACHE_CONTROL_INTERCEPTOR = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalResponse = chain.proceed(chain.request())
                if (isNetworkAvailable()) {
                    val maxAge = 60 // read from cache for 1 minute
                    return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build()
                }
            }
        }

        mMainPresenter.createCache(cacheDir, REWRITE_CACHE_CONTROL_INTERCEPTOR)

        observableFromSearchView(sv_main)
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                    mMainPresenter.loadXML(it)
            }, onError = {})
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


    override fun getBooks(query: String?) {
        mMainPresenter.loadXML()
    }

    override fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = DataAdapter(this)
        rv_home_news.adapter = mAdapter
    }

    override fun handleResponse(dataModel: DataModel?) {
        mBookArrayList = ArrayList(dataModel?.search?.books)
        mAdapter?.updateDataSet(mBookArrayList ?: ArrayList())
    }

    override fun OnBookClick(books: Book) {
        val intent = Intent(this, DetailsView::class.java)
        intent.putExtra("title", books.best_book?.title)
        intent.putExtra("author", books.best_book?.author?.name)
        intent.putExtra("rate", books.average_rating)
        intent.putExtra("url", books.best_book?.image_url)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
