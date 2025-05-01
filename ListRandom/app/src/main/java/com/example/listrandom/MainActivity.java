package com.example.listrandom;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private ImageButton delete_btn;  // new delete button
    private EditText itemEdt;
    private ArrayList<String> lngList;
    private ListView listvew;

    //String[] homework = {
    //        "Algorithms",
    //        "Data Structures",
    //        "Calculus 2",
    //        "Computer Networks",
    //        "Info Security",
    //        "Intro to CS",
    //        "Web Dev",
    //        "CS Projects",
    //        "Computer Architecture"
    //};

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        listvew = findViewById(R.id.listvew);
        add_btn = findViewById(R.id.btn_add);
        delete_btn = findViewById(R.id.btn_delete);  // make sure this id exists in your layout
        itemEdt = findViewById(R.id.idEdtItemName);

        // Initialize the ArrayList and adapter
        lngList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);
        listvew.setAdapter(adapter);

        // Add button onClick: adds the item from the text entry
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEdt.getText().toString().trim();
                if (!item.isEmpty()) {
                    lngList.add(item);
                    adapter.notifyDataSetChanged();
                    // Optionally clear the text for new input
                    itemEdt.setText("");
                }
            }
        });

        // Delete button onClick: deletes the item that matches the text entry
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToDelete = itemEdt.getText().toString().trim();
                if (!itemToDelete.isEmpty() && lngList.contains(itemToDelete)) {
                    // Remove the first occurrence of the item
                    lngList.remove(itemToDelete);
                    adapter.notifyDataSetChanged();
                    // Optionally clear the text for new entries
                    itemEdt.setText("");
                }
            }
        });

        // Optional: Set window insets for EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}