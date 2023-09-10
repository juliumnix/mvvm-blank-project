package com.example.projetov2.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.projetov2.BuildConfig;
import com.example.projetov2.MyReactActivity;
import com.example.projetov2.adapter.NavigateAdapter;
import com.example.projetov2.model.Informations;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.PackageList;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.soloader.SoLoader;

import java.util.List;

public class ReactIntegrationViewModel extends ViewModel implements NavigateAdapter {
    private final Informations model;
    private ReactRootView mReactRootView;

    public ReactIntegrationViewModel() {
        this.model = Informations.getInstance();
    }

    public void saveMessage(String message) {
        model.setMessage_From_Native(message);
    }

    @Override
    public void navigateTo(AppCompatActivity activity) {
        Intent intent = new Intent(activity, MyReactActivity.class);
        intent.putExtra("message_from_native", model.getMessage_From_Native());
        activity.startActivity(intent);
    }

    @Override
    public void initFramework(AppCompatActivity appCompatActivity) {
        SoLoader.init(appCompatActivity, false);
        mReactRootView = new ReactRootView(appCompatActivity);
        List<ReactPackage> packages = new PackageList(appCompatActivity.getApplication()).getPackages();
        // Packages that cannot be autolinked yet can be added manually here, for example:
        // packages.add(new MyReactNativePackage());
        // Remember to include them in `settings.gradle` and `app/build.gradle` too.
        SoLoader.init(appCompatActivity, false);
        ReactInstanceManager mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(appCompatActivity.getApplication())
                .setCurrentActivity(appCompatActivity)
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")
                .addPackages(packages)
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .setJavaScriptExecutorFactory(new HermesExecutorFactory())
                .build();
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        Bundle initialProperties = new Bundle();
        String messageFromNative = appCompatActivity.getIntent().getStringExtra("message_from_native");
        initialProperties.putString("message_from_native", messageFromNative);
        mReactRootView.startReactApplication(mReactInstanceManager, "MyReactNativeApp", initialProperties);
    }

    public ReactRootView getmReactRootView() {
        return mReactRootView;
    }
}
