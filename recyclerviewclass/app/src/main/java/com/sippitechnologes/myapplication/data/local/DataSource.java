package com.sippitechnologes.myapplication.data.local;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.sippitechnologes.myapplication.R;
import com.sippitechnologes.myapplication.recyclerviewsdemo.GridRecyclerViewHorizontal;
import com.sippitechnologes.myapplication.recyclerviewsdemo.GridRecyclerViewVertical;
import com.sippitechnologes.myapplication.recyclerviewsdemo.HorizontalRecyclerView;
import com.sippitechnologes.myapplication.recyclerviewsdemo.StaggeredRecyclerViewHorizontal;
import com.sippitechnologes.myapplication.recyclerviewsdemo.StaggeredRecyclerViewVertical;
import com.sippitechnologes.myapplication.recyclerviewsdemo.VerticalRecycelerView;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

   public static String[] navigationOptions = {"Horizontal RecyclerView","Vertical RecyclerView",
           "Grid  RecyclerView Horizontal",
           "Grid Recyclerview Vertical","Staggered RecyclerView Horizontal","Staggered Recyclerview Vertical"};
   public static AppCompatActivity[]activityList = {new HorizontalRecyclerView(),
           new VerticalRecycelerView(),
   new GridRecyclerViewHorizontal(),
   new GridRecyclerViewVertical(),new StaggeredRecyclerViewHorizontal(),new StaggeredRecyclerViewVertical()};
   public static List<Icon> icons = new ArrayList<>();
   public static List<Manifesto> manifestoData = new ArrayList<Manifesto>();
    public static void fillData()
    {
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
        manifestoData.add(new Manifesto("Agenda","This is Description"));
    }
    public static void fillIconData()
    {


        icons.add(new Icon(R.drawable.ant,"Ant"));
        icons.add(new Icon(R.drawable.apple,"Apple"));
        icons.add(new Icon(R.drawable.banana,"Banana"));
        icons.add(new Icon(R.drawable.grapes,"Grapes"));
        icons.add(new Icon(R.drawable.guava,"Guava"));
        icons.add(new Icon(R.drawable.mango,"Mango"));

    }

    public static void navigateToAnother(AppCompatActivity origin,AppCompatActivity destination)
    {
        Intent intent = new Intent(origin,destination.getClass());
        origin.startActivity(intent);
    }
}
