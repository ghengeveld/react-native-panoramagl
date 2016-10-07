package com.xebia.panoramagl;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.UiThread;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class RNPanoramaGLView extends RelativeLayout {
    private android.os.Handler _handler;
    private RNPanoramaGLViewManager _manager;
//    private VrPanoramaView panoWidgetView;

    @UiThread
    public RNPanoramaGLView(Context context, RNPanoramaGLViewManager manager) {
        super(context);
        _handler = new android.os.Handler();
        _manager = manager;

//        panoWidgetView = (VrPanoramaView) findViewById(R.id.pano_view);
//        panoWidgetView.setEventListener(new ActivityEventListener());

        TextView tv = new TextView(context);
        tv.setId(1);
        tv.setText("yolo");
        tv.setTextColor(Color.BLACK);
        this.addView(tv);
    }
}
