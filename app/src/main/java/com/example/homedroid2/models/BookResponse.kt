package com.example.homedroid2.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "GoodreadsResponse")
class BookResponse {

    @Element(name = "search")
    var search: Search? = null

    inner class Search {

        @Element(name = "results")
        var results: Results? = null

        inner class Results {
            @Element
            var books: List<Book>? = null
        }
    }
}
