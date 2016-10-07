package com.xebia.panoramagl;

import android.app.Activity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class RNPanoramaGLViewManager extends SimpleViewManager<RNPanoramaGLView> {
    private static final String REACT_CLASS = "RNPanoramaGL";

    private ReactApplicationContext _context;
    private Activity _activity;

    public RNPanoramaGLViewManager(ReactApplicationContext context, Activity activity) {
        super();
        _context = context;
        _activity = activity;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RNPanoramaGLView createViewInstance (ThemedReactContext context) {
        return new RNPanoramaGLView(context, this, _activity);
    }

    @Override
    protected void onAfterUpdateTransaction(RNPanoramaGLView view) {
        super.onAfterUpdateTransaction(view);
        view.onAfterUpdateTransaction();
    }

    public ReactApplicationContext getContext() {
        return _context;
    }
}
