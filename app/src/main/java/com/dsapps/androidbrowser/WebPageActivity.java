package com.dsapps.androidbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebPageActivity extends AppCompatActivity {

    private EditText enterUrlWebPage;
    private Button goButtonWebPage;
    private Button homeButton;
    private WebView webView;
    String url;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        enterUrlWebPage = (EditText) findViewById(R.id.enter_url_web_page);
        goButtonWebPage = (Button) findViewById(R.id.go_button_web_page);
        homeButton = (Button) findViewById(R.id.home_button);
        webView = (WebView) findViewById(R.id.web_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        url = getIntent().getExtras().get("url_address").toString();
        enterUrlWebPage.setText(url);
        enterUrlWebPage.setCursorVisible(false);

        enterUrlWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterUrlWebPage.setCursorVisible(true);
            }
        });

        goButtonWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enterUrlWebPage.setCursorVisible(false);

                enterUrlWebPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enterUrlWebPage.setCursorVisible(true);
                    }
                });

                String url = enterUrlWebPage.getText().toString();

                url = url.replaceAll("https://www.", "");
                url = url.replaceAll("www.", "");

                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(getApplicationContext(), "Enter URL or search terms", Toast.LENGTH_LONG).show();
                } else if (url.contains(".")) {
                    url = "https://www." + url;
                } else {
                    url = "https://www.google.co.in/search?q=" + url;
                }

                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl(url);
                webView.setWebViewClient(new WebViewClient()
                {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomePageIntent=new Intent(WebPageActivity.this, HomeActivity.class);
                startActivity(goToHomePageIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


}


