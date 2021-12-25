package com.aghiadodeh.xfadingimage.utils

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import java.net.URL

//Create a custom class and override the method to remove authentication header from the S3 image url
class GlideUrlCustomCacheKey : GlideUrl {
    constructor(url: String?) : super(url) {}
    constructor(url: String?, headers: Headers?) : super(url, headers) {}
    constructor(url: URL?) : super(url) {}
    constructor(url: URL?, headers: Headers?) : super(url, headers) {}

    override fun getCacheKey(): String {
        val url = toStringUrl()
        return if (url.contains("?")) {
            url.substring(0, url.lastIndexOf("?"))
        } else {
            url
        }
    }
}