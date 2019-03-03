package com.example.homedroid2.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.homedroid2.DataAdapter
import com.example.homedroid2.R
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.presenter.MainPresenter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainView : AppCompatActivity(), DataAdapter.BooksViewHolder.OnBookClick {

    private val TAG = MainView::class.java.simpleName

    internal var mCompositeDisposable: CompositeDisposable? = null

    private var mBookArrayList: ArrayList<Book>? = null

    private var mAdapter: DataAdapter? = null

    private var presenter: MainPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCompositeDisposable = CompositeDisposable()
        presenter = MainPresenter(this)
        presenter?.loadXML()
        setupViews()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }


    fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    internal fun handleResponse(dataModel: DataModel?) {

        mBookArrayList = ArrayList(dataModel?.search?.books)
        mAdapter = DataAdapter(mBookArrayList!!, this)
        rv_home_news.adapter = mAdapter
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
        mCompositeDisposable?.clear()
    }
}
