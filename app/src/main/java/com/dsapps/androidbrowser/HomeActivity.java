package com.dsapps.androidbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enterUrl;
    private Button goButton;
    private Button gmailButton;
    private Button facebookButton;
    private Button quoraButton;
    private Button youtubeButton;
    private Button githubButton;
    private Button twitterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        enterUrl = (EditText) findViewById(R.id.enter_url);
        goButton = (Button) findViewById(R.id.go_button);
        gmailButton = (Button) findViewById(R.id.gmail);
        facebookButton = (Button) findViewById(R.id.facebook);
        quoraButton = (Button) findViewById(R.id.quora);
        youtubeButton = (Button) findViewById(R.id.youtube);
        githubButton = (Button) findViewById(R.id.github);
        twitterButton = (Button) findViewById(R.id.twitter);


        goButton.setOnClickListener(this);
        gmailButton.setOnClickListener(this);
        facebookButton.setOnClickListener(this);
        quoraButton.setOnClickListener(this);
        youtubeButton.setOnClickListener(this);
        githubButton.setOnClickListener(this);
        twitterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.equals(goButton)) {
            openWebsite();
        }

        if (view.equals(gmailButton)) {
            Intent gmailIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            gmailIntent.putExtra("url_address", "https://www.gmail.com");
            startActivity(gmailIntent);
        }

        if (view.equals(facebookButton)) {
            Intent facebookIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            facebookIntent.putExtra("url_address", "https://www.facebook.com");
            startActivity(facebookIntent);
        }

        if (view.equals(quoraButton)) {
            Intent quoraIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            quoraIntent.putExtra("url_address", "https://www.quora.com");
            startActivity(quoraIntent);
        }

        if (view.equals(youtubeButton)) {
            Intent youtubeIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            youtubeIntent.putExtra("url_address", "https://www.youtube.com");
            startActivity(youtubeIntent);
        }

        if (view.equals(githubButton)) {
            Intent githubIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            githubIntent.putExtra("url_address", "https://www.github.com");
            startActivity(githubIntent);
        }

        if (view.equals(twitterButton)) {
            Intent twitterIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            twitterIntent.putExtra("url_address", "https://www.twitter.com");
            startActivity(twitterIntent);
        }


    }

    private void openWebsite() {
        String url = enterUrl.getText().toString();

        String urlWithoutHttps = url.replaceAll("https://www.", "");
        urlWithoutHttps = url.replaceAll("www.", "");

        if (TextUtils.isEmpty(url))
        {
            Toast.makeText(this, "Enter URL or terms to search", Toast.LENGTH_LONG).show();
        }
        else if(urlWithoutHttps.contains("."))
        {
            Intent urlIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            urlIntent.putExtra("url_address", "https://www."+urlWithoutHttps);
            startActivity(urlIntent);
        }
        else
        {
            Intent urlIntent = new Intent(HomeActivity.this, WebPageActivity.class);
            urlIntent.putExtra("url_address", "https://www.google.co.in/search?q="+urlWithoutHttps);
            startActivity(urlIntent);
        }
    }
}

