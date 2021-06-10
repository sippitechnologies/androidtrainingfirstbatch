package com.sippitechnologes.permissionhandling.takingimage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.sippitechnologes.permissionhandling.MainActivity2;
import com.sippitechnologes.permissionhandling.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeImageFromCamera extends AppCompatActivity implements View.OnClickListener {


    public static int REQUEST_IMAGE_CAPTURE=300;
    ImageView imageView;
    Button btn_camera,btn_gallery;







    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                    dispatchTakePictureIntent();
                } else {
                    Toast.makeText(this,"Permission Not Granted",Toast.LENGTH_LONG).show();
                }
            });

   ActivityResultLauncher galleryActivityLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
       @Override
       public void onActivityResult(Uri result) {


           String path = result.getPath();
           imageView.setImageURI(result);
           Intent intent = new Intent();
           intent.putExtra("path",result);



       }
   });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_image_from_camera);
        imageView = findViewById(R.id.img_preview);
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);
        btn_camera.setOnClickListener(this);
        btn_gallery.setOnClickListener(this);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Intent intent = new Intent();
            intent.putExtra("camearImage",intent.getExtras());

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void  onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_camera:
                checkCameraPermission();
               // dispatchTakePictureIntent();

            break;
            case  R.id.btn_gallery:
            galleryActivityLauncher.launch("image/*");
            break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    void askForPermission()
    {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA);
    }
    void showDialog(String title,String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(TakeImageFromCamera.this)
                .setTitle(title)
                .setMessage(message).setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        askForPermission();

                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                }).create();
        alertDialog.show();
    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkCameraPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
           dispatchTakePictureIntent();
        }
        else
        {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                askForPermission();
            } else {

                showDialog("Camera Permission Required","App request this permission to Capture Images");
            }


        }
    }
}