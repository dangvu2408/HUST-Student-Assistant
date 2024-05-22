package com.example.app_01.Fragment;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.app_01.R;
import com.example.app_01.UtilsPack.Utils;

public class Notification_Fragment extends Fragment {

    private String link;
    private WebView webView;
    private LinearLayout linear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        webView = view.findViewById(R.id.webView);
        linear = view.findViewById(R.id.linear);
        this.link = "https://ctt.hust.edu.vn/DisplayWeb/DisplayListBaiViet?tag=%C4%90T%C4%90H";
        if (Utils.getInstance().isOnline()) {
            openWeb(this.link);
        }

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) linear.getLayoutParams();
        params.topMargin = -235;
        params.bottomMargin = -255;
        linear.setLayoutParams(params);
        return view;
    }

    public void goBack() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        }
    }

    private void openWeb(String str) {
        webView.setWebViewClient(new WebNews());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(str);
    }

    private static class WebNews extends WebViewClient {
        private WebNews() {}
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            Utils.getInstance().hideLoadingDialog(webView.getContext());
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }
    }

}