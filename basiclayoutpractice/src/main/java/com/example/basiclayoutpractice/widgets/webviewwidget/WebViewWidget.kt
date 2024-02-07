package com.example.basiclayoutpractice.widgets.webviewwidget

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewWidget() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
//                settings.javaScriptEnabled = true
                this.webViewClient = CustomWebViewClient()

//                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)

            }
        },
        update = { webView ->
            webView.loadUrl("https://www.ldoceonline.com/")
        }
    )
}

class CustomWebViewClient: WebViewClient(){
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.d("LoadingUrl",url.toString())
        if(url != null && url.startsWith("https://example.com")){
            view?.loadUrl(url)
            Log.d("LoadingUrl",url)
            return true
        }
        return false
    }
}