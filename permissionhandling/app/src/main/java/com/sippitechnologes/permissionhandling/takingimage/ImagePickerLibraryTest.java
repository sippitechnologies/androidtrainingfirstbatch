package com.sippitechnologes.permissionhandling.takingimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.sippitechnologes.permissionhandling.R;

public class ImagePickerLibraryTest extends AppCompatActivity  implements View.OnClickListener {

    ImageView imageView;
    Button btnTakeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker_library_test);
        imageView = findViewById(R.id.img_capturedImage);
        btnTakeImage =findViewById(R.id.btn_take_image);
        btnTakeImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_take_image:
                ImagePicker.with(this)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
        else if(resultCode==ImagePicker.RESULT_ERROR)
        {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


}