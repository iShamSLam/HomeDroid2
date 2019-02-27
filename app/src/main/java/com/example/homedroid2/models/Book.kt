package com.example.homedroid2.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "work", strict = false)
data class Book (
    @field:Element(name = "id")
    @param:Element(name = "id")
    var id: Int? = null,
    @field:Element(name = "average_rating")
    @param:Element(name = "average_rating")
    var average_rating: String? = null,
    @field:Element(name = "best_book")
    @param:Element(name = "best_book")
    var best_book: BestBook? = null
)