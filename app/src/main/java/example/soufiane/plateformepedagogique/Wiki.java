package example.soufiane.plateformepedagogique;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Soufiane on 16/07/2016.
 */
public class Wiki extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Adds Progress Bar Support
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        // Makes Progress Bar Visible
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        // Use forum.xml as webview layout
        setContentView(R.layout.wiki);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        // Adds Zoom Control (You may not need this)
        webView.getSettings().setSupportZoom(true);

        // Enables Multi-Touch. if supported by ROM
        webView.getSettings().setBuiltInZoomControls(true);

        // Change to your own forum url

        webView.loadUrl("http:192.168.1.35:3000");

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Loads only your frum domain and no others!
                if (url.contains("http")) {
                    view.loadUrl(url);
                    // Adds Progress Bar Support
                    super.onPageStarted(view, url, null);
                    findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
                    // If they are not your domain, use browser instead
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Removes Progress Bar
                findViewById(R.id.progressbar).setVisibility(View.GONE);
                // Adds Cookies. Yummy!
                CookieSyncManager.getInstance().sync();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Enables going back history
        if (webView.copyBackForwardList().getCurrentIndex() > 0) {
            webView.goBack();
        }
        else {
            // Your exit alert code, or alternatively line below to finish
            // Finishes forum activity
            super.onBackPressed();
        }
    }
}
