package com.iramarjunior.feedreader_android.model

import android.net.Uri

data class Item(
    val title: String,
    val author: String,
    val date: Long,
    val link: Uri,
    val image: String
)