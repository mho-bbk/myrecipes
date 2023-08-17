package com.example.myrecipes

class UrlStringValidator {

    fun processUrl(url: String): String {
        var processingUrl = url.trim().lowercase()
        if (!processingUrl.startsWith("http://") && !processingUrl.startsWith("https://")) {
            processingUrl = "https://$processingUrl"
        }
        return processingUrl
    }
}