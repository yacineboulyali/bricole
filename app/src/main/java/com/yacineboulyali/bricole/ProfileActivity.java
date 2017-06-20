package com.yacineboulyali.bricole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Yacine on 19/06/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    GridView gridView;


    public float ldpi;
    public float mdpi;
    public float hdpi;
    public float xhdpi;
    public float xxhdpi;
    public float xxxhdpi;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new GridImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), FulImageActivity.class);
                i.putExtra("id", position);
                startActivity(i);


            }
        });


        ldpi= (float) 0.75;
        mdpi= (float) 1.0;
        hdpi= (float) 1.5;
        xhdpi= (float) 2.0;
        xxhdpi= (float) 3.0;
        xxxhdpi= (float) 4.0;


        double density = getResources().getDisplayMetrics().density ;

        if (density==ldpi){
            gridView.setNumColumns(2);

        }else if (density==mdpi){

        }else if (density==hdpi){

        }else if (density==xhdpi){

        }else if (density==xxhdpi){

        }else if (density==xxxhdpi){
            gridView.setNumColumns(4);


        }
        Toast.makeText(getApplicationContext(),String.valueOf(density),Toast.LENGTH_SHORT).show();











    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
