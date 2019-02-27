package com.example.homedroid2.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "search", strict = false)
data class Search (

    @field:ElementList(name = "results")
    @param:ElementList(name = "results")
    var books: List<Book>? = null
)