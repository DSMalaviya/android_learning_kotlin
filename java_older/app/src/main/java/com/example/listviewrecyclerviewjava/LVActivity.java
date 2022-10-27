package com.example.listviewrecyclerviewjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.listviewrecyclerviewjava.lvclasses.LVDataClass;
import com.example.listviewrecyclerviewjava.lvclasses.LvAdapter;

import java.util.ArrayList;

public class LVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvactivity);

        final ArrayList<LVDataClass> arrayList=new ArrayList<LVDataClass>();
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "1", "One"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "2", "Two"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "3", "Three"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "4", "Four"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "5", "Five"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "6", "Six"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "7", "Seven"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "8", "Eight"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "9", "Nine"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "10", "Ten"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "11", "Eleven"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "12", "Twelve"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "13", "Thirteen"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "14", "Fourteen"));
        arrayList.add(new LVDataClass(R.mipmap.ic_launcher, "15", "Fifteen"));

        LvAdapter listViewAdapter=new LvAdapter(this,arrayList);
        ListView numbersListview=findViewById(R.id.lv);
        numbersListview.setAdapter(listViewAdapter);
    }
}