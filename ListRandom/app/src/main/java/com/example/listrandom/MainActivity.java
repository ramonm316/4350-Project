package com.example.listrandom;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
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
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,homework);
        listvew.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}