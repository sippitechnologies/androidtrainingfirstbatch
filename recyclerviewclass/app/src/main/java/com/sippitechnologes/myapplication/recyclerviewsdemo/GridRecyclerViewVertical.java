package com.sippitechnologes.myapplication.recyclerviewsdemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.adapter.FruitAdapter;
import com.sippitechnologes.myapplication.data.local.DataSource;
import com.sippitechnologes.myapplication.data.local.Manifesto;
import com.sippitechnologes.myapplication.listener.OnRecyclerViewItemClick;

import static com.sippitechnologes.myapplication.data.local.DataSource.fillIconData;

public class GridRecyclerViewVertical extends AppCompatActivity implements OnRecyclerViewItemClick {

    RecyclerView fruitist;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillIconData();
        setContentView(R.layout.recyclerview_withgridlayout_horizontal);
        fruitist = findViewById(R.id.recyclerview_fruits);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        fruitist.setLayoutManager(gridLayoutManager);
        FruitAdapter manifestoAdapter  = new FruitAdapter(DataSource.icons);
        fruitist.setAdapter(manifestoAdapter);
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
