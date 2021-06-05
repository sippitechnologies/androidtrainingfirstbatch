package com.sippitechnologes.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sippitechnologes.myapplication.data.local.DataSource;

public class MainActivity extends AppCompatActivity {
    ListView navigationListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationListView = findViewById(R.id.navigationlistview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DataSource.navigationOptions);
        navigationListView.setAdapter(adapter);
        navigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSource.navigateToAnother(MainActivity.this,DataSource.activityList[position]);

            }
        });

    }
}