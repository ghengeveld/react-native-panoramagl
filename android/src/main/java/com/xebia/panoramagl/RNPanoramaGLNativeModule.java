package com.xebia.panoramagl;

import android.app.Activity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNPanoramaGLNativeModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "PanoramaGLManager";

    private ReactApplicationContext	mReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public RNPanoramaGLNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }
}
