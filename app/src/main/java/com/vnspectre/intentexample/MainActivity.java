package com.vnspectre.intentexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

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
     * This method is called when the Open Location in Map button is clicked. It will open the
     * a map to the location represented by the variable addressString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    public void onClickOpenAddressButton(View v) {
        String addressString = ("8 Lê Văn Linh, Thanh Khê, Cẩm Lệ, Đà Nẵng, Việt Nam");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo").encodedPath("0,0").appendQueryParameter("q", addressString);
        Uri addressUri = builder.build();
        Log.d(TAG, "onClickOpenAddressButton: " + addressUri.toString());
        showMap(addressUri);
    }

    /**
     * This method is called when the Share Text Content button is clicked. It will simply share
     * the text contained within the string textThatYouWantToShare.
     *
     * @param v
     */
    public void onClickShareTextButton(View v) {
        String textToShare = "I don't know how to share this text.";
        shareText(textToShare);
    }

    /**
     * This is where we will create and fire off our own implicit Intent.
     *
     * @param v
     */
    public void createYourOwn(View v) {
        Toast.makeText(this,
                "TODO: Create Own Implicit Intent",
                Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * This method fires off implicit Intent to open the webpage.
     *
     * @param url Url of webpage to open. Should start with http:// or https:// as that is the
     *            scheme of the Uri expected with Intent according to Common Intents page.
     */
    private void openWebPage(String url) {
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

    /**
     * This method will fires off an implicit Intent to view a location on map.
     *
     *
     * @param geoLocation
     */
    private void showMap(Uri geoLocation) {
        /*
         * Again, we create an Intent with the action, ACTION_VIEW because we want to VIEW the
         * contents of this Uri.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);

        /*
         * Using setData to set the Uri of this Intent has the exact same affect as passing it in
         * the Intent's constructor. This is simply an alternate way of doing this.
         */
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method shares text and allows a user to select which app they would like to use to
     * share the text. Using ShareCompat's IntentBuilder, we some really cool functionality for
     * free. The chooser that is started using the startChooser() method will create a chooser when
     * more one app on the device can handle the Intent.
     *
     * @param textToShare
     */
    private void shareText(String textToShare) {
        /*
         * we can think of MIME types similarly to file extensions. They aren't the exact same,
         * but MIME types help a computer determine which applications can open which content. For
         * example, if you double click on a .pdf file, you will be presented with a list of
         * programs that can open PDFs. Specifying the MIME type as text/plain has a similar affect
         * on our implicit Intent. With text/plain specified, all apps that can handle text content
         * in some way will be offered when we call startActivity on this particular Intent.
         */
        String nimeType = "text/html";

        /* This is just the title of the window that will pop up when we call startActivity */
        String title = "Learning how to Share";

        /* ShareCompat.IntentBuilder provides a fluent API for creating Intents */
        ShareCompat.IntentBuilder.from(this).setType(nimeType).setChooserTitle(title).setText(textToShare).startChooser();
    }

}
