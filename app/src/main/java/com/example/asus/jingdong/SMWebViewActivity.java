package com.example.asus.jingdong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SMWebViewActivity extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjweb_view);
        initview();
        initdata();
    }

    private void initdata() {
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        wv.loadUrl(url);
    }

    private void initview() {
        wv= (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setBuiltInZoomControls(true);
    }
}
