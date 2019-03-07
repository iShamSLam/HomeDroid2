package com.example.homedroid2.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.homedroid2.DataAdapter
import com.example.homedroid2.component.PaginationScrollListener
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.presenter.MainPresenter
import io.apptitude.premiumparking.utils.functions.observableFromSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : MvpAppCompatActivity(), MainView, DataAdapter.BooksViewHolder.OnBookClick {

    private val TAG = MainView::class.java.simpleName

    private var mBookArrayList: ArrayList<Book>? = null

    private var mAdapter: DataAdapter? = null
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private lateinit var currentSearchString: String

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setSearch()
        mMainPresenter.createCache(cacheDir)
    }

    override fun getBooks(query: String?) {
        mMainPresenter.loadXML()
    }

    fun setSearch() {
        observableFromSearchView(sv_main)
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                currentSearchString = it
                mMainPresenter.loadXML(currentSearchString)
            }, onError = {})
    }

    override fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = DataAdapter(this)
        rv_home_news.adapter = mAdapter

        var page = 1
        rv_home_news?.addOnScrollListener(object :
            PaginationScrollListener(rv_home_news.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                page++
                mMainPresenter.loadXML(currentSearchString, page.toString())
                isLoading = false
            }
        })

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
