package com.xebia.panoramagl;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;

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

    public ReactApplicationContext getContext() {
        return _context;
    }
}
