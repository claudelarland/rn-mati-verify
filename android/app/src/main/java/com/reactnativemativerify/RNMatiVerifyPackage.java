package com.reactnativemativerify;

import android.content.Context;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewManager;
import com.matilock.mati_kyc_sdk.MatiLoginButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import androidx.annotation.Nullable;

public class RNMatiVerifyPackage implements ReactPackage {
    Context context;
    public RNMatiVerifyPackage() {
    }
    @Nonnull
    @Override
    public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new RNMatiVerifyModule(reactContext));

        return modules;
    }

    @Nonnull
    @Override
    public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(new RNMatiVerifyManager());
    }


}
