package com.example.homedroid2.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.homedroid2.App
import com.example.homedroid2.DataAdapter
import com.example.homedroid2.Screen
import com.example.homedroid2.component.PaginationScrollListener
import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.presenter.MainPresenter
import com.example.homedroid2.views.DetailsView
import com.example.homedroid2.views.MainView
import io.apptitude.premiumparking.utils.functions.observableFromSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.R



class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    public lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val TAG = MainView::class.java.simpleName

    private var mBookArrayList: ArrayList<Book>? = null
    private var mAdapter: DataAdapter? = null
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private lateinit var currentSearchString: String
    private val navigator =
        object : SupportAppNavigator(this, com.example.homedroid2.R.id.container) {
            override fun applyCommands(commands: Array<Command>) {
                super.applyCommands(Array<Command>(1) { Replace(Screen.DetailsScreen()) })
                supportFragmentManager.executePendingTransactions()
            }
        }

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.INSTANCE.getAppComponent().inject(this)
        setContentView(com.example.homedroid2.R.layout.activity_main)
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

    override fun shareRouter(): Router
    {
        return router
    }

    override fun handleError(error: Throwable) {

        Log.d(TAG, error.localizedMessage)

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        rv_home_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = DataAdapter { mMainPresenter.onBookClick(it) }
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

   /* override fun navigateToDetailsView(books: Book) {
        val intent = Intent(this, DetailsView::class.java)
        intent.putExtra("title", books.best_book?.title)
        intent.putExtra("author", books.best_book?.author?.name)
        intent.putExtra("rate", books.average_rating)
        intent.putExtra("url", books.best_book?.image_url)
        startActivity(intent)
    }
    */

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}
