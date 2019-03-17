package com.example.homedroid2

import com.example.homedroid2.presenter.ZoomedPresenter
import com.example.homedroid2.views.ZoomedView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ZoomTest {
    private lateinit var presenterTest: ZoomedPresenter
    @Mock
    private lateinit var view: ZoomedView

    @Before
    @Throws(Exception::class)
    fun setU() {
        MockitoAnnotations.initMocks(this)
        presenterTest = ZoomedPresenter()
        presenterTest.attachView(view)
    }

    @Test
    fun showInfoTest() {
        Mockito.verify(view).showImage("https://pbs.twimg.com/profile_images/793021684064419840/RjEjM6z5.jpg")
    }
}