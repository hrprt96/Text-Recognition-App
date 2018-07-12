package com.developer.jatin.textrecognition.Activities;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

class Showcamera extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera bCamera;

    public Showcamera(Context context, Camera mCamera) {
        super(context);
        bCamera = mCamera;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            bCamera.setPreviewDisplay(surfaceHolder);
            bCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {




        if (mHolder.getSurface() == null) {

            return;
        }


        try {
            bCamera.stopPreview();
        } catch (Exception e) {

        }


        try {
            bCamera.setPreviewDisplay(mHolder);
            bCamera.startPreview();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}





