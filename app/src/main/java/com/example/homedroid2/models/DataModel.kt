package com.example.homedroid2.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "GoodreadsResponse", strict = false)
public data class DataModel
    (
    @field:Element(name = "search")
    @param:Element(name = "search")
    public var search: Search? = null
)