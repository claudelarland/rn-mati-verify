package com.reactnativemativerify;

import android.app.Application;

import com.matilock.mati_kyc_sdk.Mati;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

      //  Mati.init(this, "5c8a88fc0c43f7001bbab6b0");

        // uncomment for setting metadata
        /*Metadata metadata = new Metadata.Builder()
                .with("userId", "5b2a3ae479b52928106c48ee")
                .with("type", 2)
                .build();
        Mati.getInstance().setMetadata(metadata);*/
    }

}