package com.example.homedroid2.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "author", strict = false)
data class Author(
    @field:Element(name = "name")
    @param:Element(name = "name")
    var name: String? = null
)
