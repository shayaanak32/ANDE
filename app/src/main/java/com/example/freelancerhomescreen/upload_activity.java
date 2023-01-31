package com.example.freelancerhomescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class upload_activity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CROP_IMAGE_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ImageView camera = findViewById(R.id.editPhotoClickable);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAndCropImage();
            }
        });
    }
    private void chooseAndCropImage() {
        Log.d("We in","hi");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            cropImage(uri);
//        } else if (requestCode == CROP_IMAGE_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Uri croppedUri = UCrop.getOutput(data);
//                // Use the cropped image as needed
//            } else if (resultCode == UCrop.RESULT_ERROR) {
//                Throwable cropError = UCrop.getError(data);
//                Log.e("Crop Error", cropError.getMessage());
//            }
//        }
//    }
//
//    private void cropImage(Uri uri) {
//        UCrop.Options options = new UCrop.Options();
//        options.setCircleDimmedLayer(true);
//        options.setCropFrameColor(Color.GREEN);
//        options.setCropGridColor(Color.RED);
//        options.setMaxBitmapSize(1024 * 1024);
//
//        UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), "cropped_image")))
//                .withAspectRatio(1, 1)
//                .withOptions(options)
//                .start(this, CROP_IMAGE_REQUEST);
//    }

}