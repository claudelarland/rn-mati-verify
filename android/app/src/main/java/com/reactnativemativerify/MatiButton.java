package com.reactnativemativerify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.matilock.mati_kyc_sdk.LoginError;
import com.matilock.mati_kyc_sdk.LoginResult;
import com.matilock.mati_kyc_sdk.MatiCallback;
import com.matilock.mati_kyc_sdk.MatiCallbackManager;
import com.matilock.mati_kyc_sdk.MatiLoginButton;
import com.matilock.mati_kyc_sdk.MatiLoginManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;




public class MatiButton extends MatiLoginButton implements ActivityEventListener {


    private MatiCallbackManager mCallbackManager = MatiCallbackManager.createNew();

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            mCallbackManager.onActivityResult(requestCode, resultCode, intent);

        }
    };






    public MatiButton(@NonNull Context context) {
        super(context);
final MatiButton vm = this;
        MatiLoginManager.getInstance().registerCallback(mCallbackManager, new MatiCallback() {

            @Override
            public void onSuccess(LoginResult pLoginResult) {
                Log.d("Success",pLoginResult.getIdentityId());
                //Toast.makeText(getContext(),"Identification reussie.Bravo!!!! ",Toast.LENGTH_LONG).show();

                vm.onSuccess(pLoginResult.isSuccess());
            }

            @Override
            public void onCancel() {
                //Toast.makeText(getContext(),"Tu sais que tu dois le faire pour continuer ? Au revoir !!! ",Toast.LENGTH_LONG).show();
                //vm.onCancel();

                WritableMap map = Arguments.createMap();
                map.putString("data","canceled");
                final ReactContext context = (ReactContext) getContext();
                context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onCancel", map);
            }

            @Override
            public void onError(LoginError pLoginError) {
                Log.d("Error",pLoginError.getMessage());
                //Toast.makeText(getContext(),"Oooooops, une erreur est survenur ",Toast.LENGTH_LONG).show();
                vm.onError(pLoginError.getMessage());
            }
        });

    }

    public MatiButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }




    public void onSuccess(Boolean value){
        WritableMap map = Arguments.createMap();
        map.putString("isSuccess","canceled");
        final ReactContext context = (ReactContext) getContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onSuccess",
                map
        );
    }

    public void onError( String value){
        WritableMap map = Arguments.createMap();
        map.putString("error",value);
        final ReactContext context = (ReactContext) getContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onError",
                map
        );
    }


    public void onCancel(){
       // Toast.makeText(getContext(),"HiiiiiiiiiTu sais que tu dois le faire pour continuer ? Au revoir !!! ",Toast.LENGTH_LONG).show();

        WritableMap map = Arguments.createMap();
        map.putString("data","canceled");
        final ReactContext context = (ReactContext) getContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onCancel", map);
        Toast.makeText(getContext(), getId()+ "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode,requestCode,data);
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
