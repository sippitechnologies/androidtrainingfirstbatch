package com.sippitechnologes.myapplication.recyclerviewsdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.adapter.ManifestoAdapter;
import com.sippitechnologes.myapplication.data.local.Manifesto;
import com.sippitechnologes.myapplication.listener.OnRecyclerViewItemClick;

import static com.sippitechnologes.myapplication.data.local.DataSource.fillData;
import static com.sippitechnologes.myapplication.data.local.DataSource.manifestoData;

public class HorizontalRecyclerView extends AppCompatActivity implements OnRecyclerViewItemClick {

    RecyclerView listManifestoHorizontal;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillData();
        setContentView(R.layout.horizontal_recyclerview);
        listManifestoHorizontal = findViewById(R.id.recyclerviewhorizontal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        listManifestoHorizontal.setLayoutManager(linearLayoutManager);
        ManifestoAdapter manifestoAdapter  = new ManifestoAdapter(manifestoData);
        manifestoAdapter.setOnRecyclerViewItemClick(this);
        listManifestoHorizontal.setAdapter(manifestoAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(Object object, int position) {
        Manifesto manifesto = (Manifesto) object;
        Toast.makeText(this,manifesto.getDescription()+manifesto.getTitle(),Toast.LENGTH_LONG).show();

    }
}
