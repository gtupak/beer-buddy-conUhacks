package com.conuhacks;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.Inflater;

public class BeerList extends Activity {
    String[] brands;
    String[] prices;
    String[] locations;
    String[] addresses;
    String[] distances;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_beer_list);

//        Resources res = getResources();
//        prices = res.getStringArray(R.array.Price);
//        brands = res.getStringArray(R.array.Brand);
//        locations = res.getStringArray(R.array.Location);
//        addresses = res.getStringArray(R.array.Address);
//        distances = res.getStringArray(R.array.Distance);

//        list = (ListView) findViewById(R.id);
//        CustomAdapter adapter = new CustomAdapter(this, prices, brands, locations, addresses, distances);
//        list.setAdapter(adapter);


        // FireBase
        Firebase.setAndroidContext(this);
        Firebase mFireBase = new Firebase("https://beerscraper.firebaseio.com/");

        // This is where the voodoo happens
        mFireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                table = (TableLayout)findViewById(R.id.tableLayout);
                table.removeAllViews();
                ArrayList<String> items = (ArrayList<String>) dataSnapshot.getValue();
                for(Object item : items){
                    HashMap<String,Object> itemMap = (HashMap<String,Object>)item;
                    String name = (String)itemMap.get("name");
                    String price = (String)itemMap.get("price");
                    TextView txtView = new TextView(table.getContext());
                    txtView.setText(name + " at " + price);
                    TableRow row = new TableRow(table.getContext());
                    row.addView(txtView);
                    table.addView(row);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    class CustomAdapter extends ArrayAdapter<String>{
        Context context;
        String[] pricearray;
        String[] brandarray;
        String[] locationarray;
        String[] addressarray;
        String[] distancearray;

        CustomAdapter(Context c, String[] prices, String[] brands, String[] locations, String[] addresses, String[] distances){
            super(c, R.layout.single_row, R.id.price, prices);
            this.context = c;
            this.pricearray = prices;
            this.brandarray = brands;
            this.locationarray = locations;
            this.addressarray = addresses;
            this.distancearray = distances;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);

            TextView myprice = (TextView) row.findViewById(R.id.price);
            TextView mybrand = (TextView) row.findViewById(R.id.Brand);
            TextView myloca = (TextView) row.findViewById(R.id.Location);
            TextView myaddr = (TextView) row.findViewById(R.id.Address);
            TextView mydist = (TextView) row.findViewById(R.id.distance);

            myprice.setText(pricearray[position]);
            mybrand.setText(brandarray[position]);
            myloca.setText(locationarray[position]);
            myaddr.setText(addressarray[position]);
            mydist.setText(distancearray[position]);

            return row;
        }
    }
}
