package com.conuhacks;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchBeerList extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_beer_list);
        TextView tv1 = new TextView(this);
        tv1.setText("FIRST");
        tv1.setTextSize(10);

        TextView tv2 = new TextView(this);
        tv2.setText("MIDDLE");

        TextView tv3 = new TextView(this);
        tv3.setTextSize(10);
        tv3.setText("LAST");

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(tv3);
        
    }
}
