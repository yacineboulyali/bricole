package com.yacineboulyali.bricole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

/**
 * Created by Yacine on 19/06/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    //GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
          //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();
        //    }
        //});


      //  gridView = (GridView) findViewById(R.id.gridview);
        //  gridView.setAdapter(new GridImageAdapter(this));

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //          @Override
        //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
        //              Intent i = new Intent(getApplicationContext(),FulImageActivity.class);
        //      i.putExtra("id",position);
        //      startActivity(i);



        //  }
        //});

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
