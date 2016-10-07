package com.xebia.panoramagl;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class RNPanoramaGLViewManager extends SimpleViewManager<RNPanoramaGLView> {
    private static final String REACT_CLASS = "RNPanoramaGL";

    private ReactApplicationContext _context;

    public RNPanoramaGLViewManager(ReactApplicationContext context) {
        super();
        _context = context;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public RNPanoramaGLView createViewInstance (ThemedReactContext context) {
        return new RNPanoramaGLView(context, this);
    }

    public ReactApplicationContext getContext() {
        return _context;
    }
}
