package com.example.homedroid2.models

import org.simpleframework.xml.Element

@Element(name = "work")
public class Book {

    @Element(name = "best_book")
    var best_book: BestBook? = null

    inner class BestBook {

        @Element(name = "title")
        var title: String? = null

        @Element(name = "image_url")
        var image_url: String? = null
    }
}