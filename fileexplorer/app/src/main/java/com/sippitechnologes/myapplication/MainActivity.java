package com.sippitechnologes.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   ListView folderList;


    private String m_root;
    ActivityResultLauncher<String> filePermissionLauncher= registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted->{if(isGranted)
       if(isGranted)
       {
            showToast(this,"We are ready To Go");
       }
       else
       {
            showToast(this,getString(R.string.file_permission_not_granted));
       }
    });

    boolean isPermissionRequired()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            return true;

        }
        else
        {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFilePermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {

        }
        else if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            showPermissionInfo(this,"Permission Required ","To Create File and Folder ","No","Yes");
        }
        else {
            requestForFilePermission();
        }

    }

    private void showPermissionInfo(Context context,String title,String message,String negativeLabel,String positiveLabel)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).setNegativeButton(negativeLabel, (dialog, which) -> {
            dialog.dismiss();
        }).setPositiveButton(positiveLabel,((dialog, which) -> {
            dialog.dismiss();
          requestForFilePermission();

        })).create();
        alertDialog.show();

    }

    private void requestForFilePermission()
    {
        filePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        folderList = findViewById(R.id.folderlist);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_create_file:
                if(isPermissionRequired())
                {
                    checkFilePermission();
                }
                break;
            case R.id.menu_create_folder:
                if(isPermissionRequired())
                {
                    checkFilePermission();
                }
                break;
        }
        return true;
    }




}