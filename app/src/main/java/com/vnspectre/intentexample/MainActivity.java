package com.vnspectre.intentexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the Open Website is clicked. It will open the website
     * specified by URL represented by the urlAsString using implicit Intents.
     *
     * @param v
     */
    public void onClickOpenWebpageButton(View v) {
        String urlAsString = "http://minori.com.vn";
        openWebPage(urlAsString);
    }

    /**
     * This method fires off implicit Intent to open the webpage.
     *
     * @param url Url of webpage to open. Should start with http:// or https:// as that is the
     *            scheme of the Uri expected with Intent according to Common Intents page.
     */
    public void openWebPage(String url) {
        /* We want to demonstrate the Uri.parse method because its usage occurs frequently.
         * We could have just easily passed in a Uri as the parameter of this method.
         */
        Uri webpage = Uri.parse(url);

        /*
         * Here, we create the Intent with the action of ACTION_VIEW. The action allows the user
         * to particular content. In this case, our Webpage url*/
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        /*
         * This is a check we perform with every implicit Intent that we launch. In some cases,
         * the device where this code is running might not have an Activity to perform the action
         * with the data we've specified. Without this check, in those cases your app would crash.
         */
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
