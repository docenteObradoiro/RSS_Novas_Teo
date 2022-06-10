package com.iramarjunior.feedreader_android.model

import android.net.Uri

data class Item(
    val title: String,
    val link: Uri,
    val pubDate: Long
)