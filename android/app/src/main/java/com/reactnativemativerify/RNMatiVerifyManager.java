package com.reactnativemativerify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.matilock.mati_kyc_sdk.LoginError;
import com.matilock.mati_kyc_sdk.LoginResult;
import com.matilock.mati_kyc_sdk.Mati;
import com.matilock.mati_kyc_sdk.MatiCallback;
import com.matilock.mati_kyc_sdk.MatiCallbackManager;
import com.matilock.mati_kyc_sdk.MatiLoginButton;
import com.matilock.mati_kyc_sdk.MatiLoginManager;

import org.xmlpull.v1.XmlPullParser;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.samsung.android.sdk.camera.impl.internal.NativeUtil.TAG;

public class RNMatiVerifyManager extends SimpleViewManager<MatiButton> implements ActivityEventListener {
    private ThemedReactContext mContext = null;
    private String apiKey = null;



    private MatiCallbackManager mCallbackManager = MatiCallbackManager.createNew();
    Context context;
    public RNMatiVerifyManager() {
        super();


    }


    @Override
    public String getName() {
        return "RNMatiVerify";
    }

    @Override
    protected MatiButton createViewInstance(ThemedReactContext reactContext) {

        context= reactContext;

        //Toast.makeText(reactContext,"Bienvenu dans le test Djoba "+ this.apiKey,Toast.LENGTH_LONG).show();

        Mati.init(reactContext, "5c8a88fc0c43f7001bbab6b0");

        MatiLoginManager.getInstance().registerCallback(mCallbackManager, new MatiCallback() {

            @Override
            public void onSuccess(LoginResult pLoginResult) {
                Log.d("Success",pLoginResult.getIdentityId());
                Toast.makeText(mContext,"Success test ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(mContext,"Eror test ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(LoginError pLoginError) {
                Log.d("Error",pLoginError.getMessage());
                Toast.makeText(mContext,"Eror test ",Toast.LENGTH_LONG).show();

            }
        });


        return new MatiButton(reactContext);
    }






    @ReactProp(name="key")
    public void setKey(MatiLoginButton button, String value){
        Toast.makeText(context,value+" heelllele",Toast.LENGTH_LONG).show();
    }


    @ReactProp(name="title")
    public void setTitle(MatiLoginButton button, String value){

        button.setText(value);
    }


    @Override
    public Map<String, String> getNativeProps() {
        return super.getNativeProps();
    }

    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onCancel",
                        MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onCancel")))
                .put("onSuccess",
                        MapBuilder.of("phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onSuccess")))
                .put("onError",
                        MapBuilder.of("phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onError")))
                .build();
    }

    @Override
    public Map<String,Integer> getCommandsMap() {
        Log.d("React"," View manager getCommandsMap:");
        return MapBuilder.of(
                "saveImage",
                1,
                "resetImage",
                2);
    }




    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
