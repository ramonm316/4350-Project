package com.example.listrandom;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton add_btn;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    ListView listvew;
    String[] homework = {"Algorithms",
            "Data Structures",
            "Calculus 2",
            "Computer Networks",
            "Info Security",
            "Intro to CS",
            "Web Dev",
            "CS Projects",
            "Computer Architecture",
    };

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listvew = findViewById(R.id.listvew);
        add_btn = findViewById(R.id.btn_add);
        itemEdt = findViewById(R.id.idEdtItemName);
        lngList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lngList);
        listvew.setAdapter(adapter);
        add_btn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // on below line we are getting text from edit text
                                           String item = itemEdt.getText().toString();

                                           // on below line we are checking if item is not empty
                                           if (!item.isEmpty()) {

                                               // on below line we are adding item to our list.
                                               lngList.add(item);

                                               // on below line we are notifying adapter
                                               // that data in list is updated to
                                               // update our list view.
                                               adapter.notifyDataSetChanged();
                                           }

                                       }
                                   });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

    }
}