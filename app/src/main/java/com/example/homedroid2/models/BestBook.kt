package com.example.homedroid2.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "best_book", strict = false)
data class BestBook(
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,
    @field:Element(name = "image_url")
    @param:Element(name = "image_url")
    var image_url: String? = null,
    @field:Element(name = "author")
    @param:Element(name = "author")
    var author: Author? = null
)
