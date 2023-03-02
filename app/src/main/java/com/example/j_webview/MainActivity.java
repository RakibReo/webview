package com.example.j_webview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.j_webview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;

    String url = "https://www.creativeitinstitute.com/";     //jai website app convert korbo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webView.getSettings().setJavaScriptEnabled(true);    //javascipt dia website lekha aita acess korte
        binding.webView.loadUrl(url);     //amar webview website view korte


        //buffer or progressBar loading korte ar kotokkon visible and invisible thakbe
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.progressCircular.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressCircular.setVisibility(View.GONE);
            }

        });



        //amr app browser kaj korte
        binding.webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.progressCircular.setProgress(newProgress);
            }
        });



    }

    @Override
    public void onBackPressed() {                             //direct jano exit na hoi
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        }else {
            showAlert();                   //ai function exit hoar age akta alert dibe
        }

    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure to exit?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }
}