package com.example.hackathon;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class MoreFragment extends Fragment {

    private WebView webView;
    private ImageView insuranceIV;
    private ImageView diseasesIV;
    private ImageView aboutUsIV;

    private boolean isWebViewVisible = false;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        webView = view.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());

        insuranceIV = view.findViewById(R.id.insuranceIV);
        diseasesIV = view.findViewById(R.id.diseasesIV);
        aboutUsIV = view.findViewById(R.id.aboutUsIV);

        insuranceIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView("https://hackman-statics.onrender.com/insurance");
            }
        });

        diseasesIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView("https://plant-disease-kisanmitre.onrender.com/");
            }
        });

        aboutUsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView("https://hackman-statics.onrender.com/appointment");
            }
        });

        if (isWebViewVisible) {
            webView.setVisibility(View.VISIBLE);
            insuranceIV.setVisibility(View.INVISIBLE);
            diseasesIV.setVisibility(View.INVISIBLE);
            aboutUsIV.setVisibility(View.INVISIBLE);
        } else {
            webView.setVisibility(View.GONE);
        }

        return view;
    }

    private void openWebView(String url) {
        isWebViewVisible = true;
        webView.setVisibility(View.VISIBLE);
        insuranceIV.setVisibility(View.INVISIBLE);
        diseasesIV.setVisibility(View.INVISIBLE);
        aboutUsIV.setVisibility(View.INVISIBLE);
        webView.loadUrl(url);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}
