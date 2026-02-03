package com.example.dictionary;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ArrayList<HashMap< String,String >> arrayList;
    HashMap<String,String> hashMap;
    EditText etSearch;
    ListView listView;
    DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------Set the desired status bar color start------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#1A73E8"));
        }
        // ---------Set the desired status bar color finish -----------

        //-------------id --------------------
        etSearch = findViewById(R.id.etSearch);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);






        loadData( dbHelper.getAllData() );


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String key = etSearch.getText().toString();
                loadData( dbHelper.searchWord(key) );

            }
        });





        //--------------------------------------------
    }
        //---------------------------------------------


    private void loadData(Cursor cursor){

        if (cursor!=null && cursor.getCount()>0){

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()){
                hashMap = new HashMap<>();
                hashMap.put("word",cursor.getString(1));
                hashMap.put("meaning",cursor.getString(2));
                hashMap.put("partsOfSpeech",cursor.getString(3));
                hashMap.put("example",cursor.getString(4));
                arrayList.add(hashMap);
            }

            listView.setAdapter(new WordListAdapter());
        }

    }


    private class WordListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item_layout,parent,false);


            TextView tvWord = myView.findViewById(R.id.tvWord);
            TextView tvPartsOfSpeech = myView.findViewById(R.id.tvPartsOfSpeech);
            TextView tvMeaning = myView.findViewById(R.id.tvMeaning);
            TextView tvExample = myView.findViewById(R.id.tvExample);


            hashMap = arrayList.get(position);
            String word = hashMap.get("word");
            String partsOfSpeech = hashMap.get("partsOfSpeech");
            String meaning = hashMap.get("meaning");
            String example = hashMap.get("example");

            tvWord.setText(word);
            tvPartsOfSpeech.setText(partsOfSpeech);
            tvMeaning.setText(meaning);
            tvExample.setText(example);




            return myView;
        }
    }







}