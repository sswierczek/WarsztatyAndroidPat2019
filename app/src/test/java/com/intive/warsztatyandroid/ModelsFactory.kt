package com.intive.warsztatyandroid

import com.intive.warsztatyandroid.home.model.data.HomeItem

fun createEmptyHomeItem(
    albumId: Int = 0,
    id: Int = 0,
    thumbnailUrl: String = "",
    title: String = "",
    url: String = ""
) = HomeItem(
    albumId = albumId,
    id = id,
    thumbnailUrl = thumbnailUrl,
    title = title,
    url = url
)