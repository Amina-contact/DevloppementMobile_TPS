package com.example.exercice1_18_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        EditText textView = findViewById(R.id.editTextTextOp);
        ListView listView = findViewById(R.id.listview);
        List list = new ArrayList();
        //list.add("Amina");
        //list.add("Fatima");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textView.getText().toString();
                if (name.contains("-")){
                    int a=Integer.valueOf(name.split("\\-")[0]);
                    int b=Integer.valueOf(name.split("\\-")[1]);
                    list.add(name+"="+String.valueOf(a-b));
                }
                if (name.contains("+")){
                    int a=Integer.valueOf(name.split("\\+")[0]);
                    int b=Integer.valueOf(name.split("\\+")[1]);
                    list.add(name+"="+String.valueOf(a+b));
                }

                if (name.contains("*")){
                    int a=Integer.valueOf(name.split("\\*")[0]);
                    int b=Integer.valueOf(name.split("\\*")[1]);
                    list.add(name+"="+String.valueOf(a*b));
                }
                if (name.contains("/")){
                    int a=Integer.valueOf(name.split("\\/")[0]);
                    int b=Integer.valueOf(name.split("\\/")[1]);
                    list.add(name+"="+String.valueOf(a/b));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}