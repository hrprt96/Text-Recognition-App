package com.developer.jatin.textrecognition.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.developer.jatin.textrecognition.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Start extends AppCompatActivity {

    private Camera mCamera;
    private Showcamera mPreview;
    FrameLayout frameLayout;
    Button captureButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        mCamera = Camera.open( );

        // Create our Preview view and set it as the content of our activity.
        mPreview = new Showcamera(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        captureButton = (Button)findViewById(R.id.button_capture);


    }
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {


        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
            }
        }
    };

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {

            File picture_file = getOutputMediaFile(MEDIA_TYPE_IMAGE) ;

            if (picture_file== null)
            {
                return;
            }
            else {
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(bytes);
                    fos.close();

                    camera.startPreview();
                }catch (java.io.IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
    };


    private File getOutputMediaFile(int mediaTypeImage)
    {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED))
        {
            return null;

        }
        else
        {
            File folder_gui =  new File(Environment.getExternalStorageDirectory() + File.separator + "GUI");
            if (!folder_gui.exists() )
            {folder_gui.mkdirs();}
            File outputFile = new File(folder_gui , "temp.jpg");
            return  outputFile;

        }
    }

    // Add a listener to the Capture button

    /*captureButton.setOnClickListener(
        new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // get an image from the camera
            mCamera.takePicture(null, null, mPicture);
        }
    }
); */

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }



    public void click (View view) {
        mCamera.takePicture(null, null, mPicture);
    }


   /* Public void Showcamera(View view)
    {
        if (checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)
        {
            showCameraPreview();

        } else
        {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                Toast.makeText(this , "camera permission is needed" , Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.CAMERA} , REQUEST_CAMERA);
        }
    }
    public void OnRequestPermissionsResultCallback(int re)*/

   private static final int REQUEST_CAMERA = 125;
    private static final int REQUEST_STORAGE = 225;
    private static final int TXT_CAMERA = 1;
    private static final int TXT_STORAGE = 2;

}
