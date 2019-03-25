package com.reactnativemativerify;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.matilock.mati_kyc_sdk.LoginError;
import com.matilock.mati_kyc_sdk.LoginResult;
import com.matilock.mati_kyc_sdk.Mati;
import com.matilock.mati_kyc_sdk.MatiCallback;
import com.matilock.mati_kyc_sdk.MatiCallbackManager;
import com.matilock.mati_kyc_sdk.MatiLoginButton;
import com.matilock.mati_kyc_sdk.MatiLoginManager;

import javax.annotation.Nonnull;

import static java.security.AccessController.getContext;

public class RNMatiVerifyModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private String apiKey = null;
    private MatiCallbackManager mCallbackManager = MatiCallbackManager.createNew();

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            mCallbackManager.onActivityResult(requestCode, resultCode, intent);

        }
    };
    private Callback callback;
private MatiButton button =null;
    public RNMatiVerifyModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
        button=  new MatiButton(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "RNMatiVerify";
    }

    @ReactMethod
    public void show(String message){
       // Toast.makeText(getReactApplicationContext(), message,Toast.LENGTH_LONG).show();
    }

    public void onSuccess(Boolean value){
        WritableMap map = Arguments.createMap();
        map.putString("isSuccess","canceled");
        final ReactContext context = (ReactContext) getReactApplicationContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                1,
                "onCancel",
                map
        );
    }

    public void onError( String value){
        WritableMap map = Arguments.createMap();
        map.putString("error",value);
        final ReactContext context = (ReactContext) getReactApplicationContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                2,
                "onCancel",
                map
        );
    }






    @ReactMethod
    public void openWindow(){

        MatiLoginManager.getInstance().login(getCurrentActivity());
    }


    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
