package com.example.homedroid2

import com.example.homedroid2.presenter.DetailsPresenter
import com.example.homedroid2.views.DetailsView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {
    private lateinit var presenterTest: DetailsPresenter
    @Mock
    private lateinit var view: DetailsView
    private val testString: String = "Undefined"

    @Before
    @Throws(Exception::class)
    fun setU() {
        MockitoAnnotations.initMocks(this)
        presenterTest = DetailsPresenter()
        presenterTest.attachView(view)
        presenterTest.showInfo(testString, testString, testString, testString)
    }

    @Test
    fun showInfoTest() {
        verify(view).showInfo(testString, testString, testString, testString)
    }
}