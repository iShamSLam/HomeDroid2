package com.example.homedroid2

import com.example.homedroid2.models.Book
import com.example.homedroid2.models.DataModel
import com.example.homedroid2.presenter.MainPresenter
import com.example.homedroid2.views.MainView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    @Mock
    private lateinit var view: MainView
    private val testString: String = "Say Hello to my lil friend motherf*cker"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter()
        presenter.attachView(view)
    }

    @Test
    @Throws(Exception::class)
    fun onItemClick() {
        val book = Mockito.mock(Book::class.java)
        presenter.onBookClick(book)
        Mockito.verify<Any>(view.navigateToDetailsView(book))
    }

    @Test
    @Throws(Exception::class)
    fun onFirstViewAttach() {
        Mockito.doNothing().`when`<Any>(presenter.loadXML())
        presenter.onFirstViewAttach()
        Mockito.verify<Any>(presenter.loadXML())
    }

    @Test
    fun testLoadXml() {
        verify(view).getBooks(testString)
    }

    @Test
    @Throws(Exception::class)
    fun doActionInView() {
        Mockito.verifyNoMoreInteractions(view)
    }
}