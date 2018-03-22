package com.example.whip.translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> friends;
    ArrayAdapter listAdapter;
    ArrayAdapter adapter;

    @BindView(R.id.text)
    TextView text;

    @BindView(R.id.nb_friends) TextView nb_friends;

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @BindView (R.id.myauto2) AutoCompleteTextView myauto2;

    @BindView(R.id.list)
    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(list == null)
            return;

        friends = new ArrayList<>();

        String text_nb_friends = getResources().getQuantityString(R.plurals.nb_friends,friends.size(),friends.size());
        nb_friends.setText(text_nb_friends);

        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,friends);
        autoCompleteTextView.setAdapter(adapter);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,friends);

        list.setAdapter(listAdapter);


        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for (Locale loc : locale){
            country = loc.getDisplayCountry();
            if (country.length()>0 && !countries.contains(country)){
                countries.add(country);
            }
        }

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,countries);
        myauto2.setAdapter(myadapter);



    }


    private static final String[] COUNTRIES = new String[]{
            "Belgium","France","Italy","Germany","Spain"
    };


    @OnItemClick(R.id.list)
    public void onItemClick(int i){
        String name= friends.get(i);
        autoCompleteTextView.setText(name);
    }

    @OnClick(R.id.greet)
    public void onButtonClick(Button btn){
        String name= autoCompleteTextView.getText().toString();
        String greeting = getResources().getString(R.string.greeting,name);
        Toast.makeText(this,greeting, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.add)
    public void onFriendAdded(Button btn){
        String name=autoCompleteTextView.getText().toString();
        friends.add(name);
        listAdapter.notifyDataSetChanged();
        //adapter.notifyDataSetChanged();
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,friends);
        autoCompleteTextView.setAdapter(adapter);

        String text_nb_friends = getResources().getQuantityString(R.plurals.nb_friends,friends.size(),friends.size());
        nb_friends.setText(text_nb_friends);

    }

}
