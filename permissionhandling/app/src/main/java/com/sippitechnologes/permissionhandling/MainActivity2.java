package com.sippitechnologes.permissionhandling;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button cameraPermission;
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"Permission Not Granted",Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraPermission = findViewById(R.id.btncheckcamerapermission);
        cameraPermission.setOnClickListener(this);

    }

    /**
     *  This method is used to check current API Version if its is more greater or equal to Api Version 23 then Permission is required means function return true
     *  else return false
     *
     * @return
     */
    boolean isPermissionRequired()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            return  true;
        }
        else
        {
            return  false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btncheckcamerapermission:
            {
               if(isPermissionRequired())
               {
                   checkCameraPermission();
               }
            }
            break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkCameraPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(MainActivity2.this,"Permission is Granted",Toast.LENGTH_LONG).show();
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


    void showDialog(String title,String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity2.this)
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
    void askForPermission()
    {
       requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }





}





