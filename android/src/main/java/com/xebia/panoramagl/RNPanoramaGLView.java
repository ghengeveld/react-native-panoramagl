package com.xebia.panoramagl;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.util.Log;
import android.util.Pair;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.pano.VrPanoramaView.Options;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RNPanoramaGLView extends RelativeLayout {
    private static final String TAG = RNPanoramaGLView.class.getSimpleName();

    private android.os.Handler _handler;
    private RNPanoramaGLViewManager _manager;
    private Activity _activity;

    /** Actual panorama widget. **/
    private VrPanoramaView panoWidgetView;
    /**
     * Arbitrary variable to track load status. In this example, this variable should only be accessed
     * on the UI thread. In a real app, this variable would be code that performs some UI actions when
     * the panorama is fully loaded.
     */
    public boolean loadImageSuccessful;
    /** Tracks the file to be loaded across the lifetime of this app. **/
    private Uri fileUri;
    /** Configuration information for the panorama. **/
    private Options panoOptions = new Options();
    private ImageLoaderTask backgroundImageLoaderTask;

    @UiThread
    public RNPanoramaGLView(Context context, RNPanoramaGLViewManager manager, Activity activity) {
        super(context);
        _handler = new android.os.Handler();
        _manager = manager;
        _activity = activity;
    }

    public void onAfterUpdateTransaction() {
        panoWidgetView = new VrPanoramaView(_activity);
        panoWidgetView.setEventListener(new ActivityEventListener());
        panoWidgetView.setStereoModeButtonEnabled(false);
        panoWidgetView.setInfoButtonEnabled(false);
        this.addView(panoWidgetView);

        fileUri = null;
        panoOptions.inputType = Options.TYPE_MONO;

        // Load the bitmap in a background thread to avoid blocking the UI thread. This operation can
        // take 100s of milliseconds.
        if (backgroundImageLoaderTask != null) {
            // Cancel any task from a previous intent sent to this activity.
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new ImageLoaderTask(this.getContext());
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }

    /**
     * Helper class to manage threading.
     */
    class ImageLoaderTask extends AsyncTask<Pair<Uri, Options>, Void, Boolean> {
        private Context context;

        ImageLoaderTask(Context context) {
            this.context = context;
        }

        /**
         * Reads the bitmap from disk in the background and waits until it's loaded by pano widget.
         */
        @Override
        protected Boolean doInBackground(Pair<Uri, Options>... fileInformation) {
            Options panoOptions = null;  // It's safe to use null VrPanoramaView.Options.
            InputStream istr = null;
            if (fileInformation == null || fileInformation.length < 1
                    || fileInformation[0] == null || fileInformation[0].first == null) {
                AssetManager assetManager = this.context.getAssets();
                try {
                    istr = assetManager.open("R0010036.jpg");
                    panoOptions = new Options();
                    panoOptions.inputType = Options.TYPE_MONO;
                } catch (IOException e) {
                    Log.e(TAG, "Could not decode default bitmap: " + e);
                    return false;
                }
            } else {
                try {
                    istr = new FileInputStream(new File(fileInformation[0].first.getPath()));
                    panoOptions = fileInformation[0].second;
                } catch (IOException e) {
                    Log.e(TAG, "Could not load file: " + e);
                    return false;
                }
            }

            panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeStream(istr), panoOptions);
            try {
                istr.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close input stream: " + e);
            }

            return true;
        }
    }

    /**
     * Listen to the important events from widget.
     */
    private class ActivityEventListener extends VrPanoramaEventListener {
        /**
         * Called by pano widget on the UI thread when it's done loading the image.
         */
        @Override
        public void onLoadSuccess() {
            loadImageSuccessful = true;
        }

        /**
         * Called by pano widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
            loadImageSuccessful = false;
//            Toast.makeText(
//                    RNPanoramaGLView.this, "Error loading pano: " + errorMessage, Toast.LENGTH_LONG)
//                    .show();
            Log.e(TAG, "Error loading pano: " + errorMessage);
        }
    }
}
