package com.conuhacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class HomePage extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btn_beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);

//        // FireBase
//        Firebase.setAndroidContext(this);
//        Firebase mFireBase = new Firebase("https://beerscraper.firebaseio.com/");
//
//        mFireBase.child("beerscraper").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Radius, android.R.layout.simple_spinner_item );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btn_beer = (Button) findViewById(R.id.beer_btn);

        btn_beer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FindBeer();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView text = (TextView) view;
        Toast.makeText(this, "You Selected "+ text.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void FindBeer (){
        //Intent intent = new Intent(HomePage.this, BeerList.class);
        Intent intent = new Intent(HomePage.this, SearchBeerList.class);
        startActivity(intent);
    }
}