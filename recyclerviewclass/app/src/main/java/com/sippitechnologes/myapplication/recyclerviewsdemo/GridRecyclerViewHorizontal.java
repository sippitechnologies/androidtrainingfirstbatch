package com.sippitechnologes.myapplication.recyclerviewsdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.adapter.FruitAdapter;
import com.sippitechnologes.myapplication.adapter.ManifestoAdapter;
import com.sippitechnologes.myapplication.data.local.DataSource;
import com.sippitechnologes.myapplication.data.local.Icon;
import com.sippitechnologes.myapplication.data.local.Manifesto;
import com.sippitechnologes.myapplication.listener.OnRecyclerViewItemClick;

import static com.sippitechnologes.myapplication.data.local.DataSource.fillData;
import static com.sippitechnologes.myapplication.data.local.DataSource.fillIconData;
import static com.sippitechnologes.myapplication.data.local.DataSource.manifestoData;

public class GridRecyclerViewHorizontal extends AppCompatActivity implements OnRecyclerViewItemClick {

    RecyclerView fruitist;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillIconData();
        setContentView(R.layout.recyclerview_withgridlayout);
        fruitist = findViewById(R.id.recyclerview_fruits);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);
        fruitist.setLayoutManager(gridLayoutManager);

        FruitAdapter fruitAdapter  = new FruitAdapter(DataSource.icons);
        fruitAdapter.setOnRecyclerViewItemClick(this);
        fruitist.setAdapter(fruitAdapter);
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

        Icon icon = (Icon) object;
        Toast.makeText(this,icon.getName(),Toast.LENGTH_LONG).show();

    }
}
