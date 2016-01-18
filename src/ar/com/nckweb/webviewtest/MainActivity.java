package ar.com.nckweb.webviewtest;

import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebResourceResponse;
// XXX: esto es para la api nueva
//import android.webkit.WebResourceRequest;

import android.content.res.AssetManager;
import android.util.Log;

import android.preference.PreferenceManager;
import android.content.SharedPreferences;

//import android.view.View;
//import android.view.ContextMenu;
//import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.Menu;

import java.net.URL;
import java.io.InputStream;

import android.content.Intent;
import ar.com.nckweb.webviewtest.SettingsActivity;

public class MainActivity extends Activity
{
    protected static final int MENU_ITEM_SETTING_ID = 1;
    protected static final int MENU_ITEM_RELOAD_ID = 2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Hay que setear los valores de preference por defecto para el SharedPreferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){

            public WebResourceResponse interceptRequest(String url){
                try {
                    URL myUrl = new URL(url);
                    String file = myUrl.getFile().substring(1);

                    Log.i("ar.com.nckweb.webviewtest.MainActivity", "file :: " + file);
                    InputStream iStream = getResources().getAssets().open(file);
                    return new WebResourceResponse("text/html", "utf8", iStream);
                } catch (Exception e){
                    Log.i("ar.com.nckweb.webviewtest.MainActivity", "Exception ocurred :: " + e.toString());
                }

                Log.i("ar.com.nckweb.webviewtest.MainActivity", "url :: " + url);
                return null;
            }

            // XXX: esto es para la api nueva
            //public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request){
            //    return interceptRequest(request.getUrl().toString());
            //};

            public WebResourceResponse shouldInterceptRequest(WebView view, String url){
                return interceptRequest(url);
            };
        });

        loadStartPage();
    }

    public String getHost(){
        return PreferenceManager.getDefaultSharedPreferences(this).getString("pref_host", "");
    }

    public void loadStartPage(){
        WebView myWebView = (WebView) findViewById(R.id.webview);
        //myWebView.loadUrl("http://192.168.1.103:8000/index.html");
        String host = getHost();
        myWebView.loadUrl("http://"+host+"/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ITEM_SETTING_ID, 0, "Settings");
        menu.add(0, MENU_ITEM_RELOAD_ID, 0, "Reload");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.i("ar.com.nckweb.webviewtest.MainActivity", "clicked on " + item.getItemId());
        switch(item.getItemId()){
            case MENU_ITEM_SETTING_ID:
                Log.i("ar.com.nckweb.webviewtest.MainActivity", "Abriendo Settings");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case MENU_ITEM_RELOAD_ID:
                loadStartPage();
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed(){
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.goBack();
    }
}
