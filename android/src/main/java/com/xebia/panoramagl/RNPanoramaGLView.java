package com.xebia.panoramagl;

import android.content.Context;
import android.support.annotation.UiThread;
import android.widget.RelativeLayout;

public class RNPanoramaGLView extends RelativeLayout {
    private android.os.Handler _handler;
    private RNPanoramaGLViewManager _manager;

    @UiThread
    public RNPanoramaGLView(Context context, RNPanoramaGLViewManager manager) {
        super(context);
        _handler = new android.os.Handler();
        _manager = manager;
    }
}
