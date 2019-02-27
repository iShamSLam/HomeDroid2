package com.example.homedroid2.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.homedroid2.DataAdapter
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.models.GoodreadsApiService
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import com.example.homedroid2.R


class MainView : AppCompatActivity(), DataAdapter.BooksViewHolder.OnBookClick {

    private val TAG = MainView::class.java.simpleName

    private val BASE_URL = "https://www.goodreads.com/"

    private var result: DataModel? = DataModel()

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mBookArrayList: ArrayList<Book>? = null

    private var mAdapter: DataAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()

        setupViews()

        loadXML()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun loadXML() {
        val thread = Thread(Runnable {
            try {
                val requestInterface = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build().create(GoodreadsApiService::class.java)

                result = requestInterface.search("enter").execute().body()
                runOnUiThread {
                    handleResponse(result?.search?.books)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()
    }

    private fun handleResponse(bookList: List<Book>?) {

        mBookArrayList = ArrayList(bookList)
        mAdapter = DataAdapter(mBookArrayList!!, this)

        rv_home_news.adapter = mAdapter
    }

    override fun OnBookClick(books: Book) {

    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }
}
