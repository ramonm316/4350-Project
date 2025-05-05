package com.example.listrandom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.TransitionManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton add_btn;
    private ImageButton delete_btn;
    private ImageButton edit_btn;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    private ListView listvew;
    private int itemIndex = -1;
    ArrayAdapter<String> adapter;

    private final String DELIMITER=";;";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Uncomment if you use EdgeToEdge mode.
        setContentView(R.layout.activity_main);

        // Initialize views
        listvew = findViewById(R.id.listvew);
        add_btn = findViewById(R.id.btn_add);
        delete_btn = findViewById(R.id.btn_delete);
        edit_btn = findViewById(R.id.btn_edit);
        itemEdt = findViewById(R.id.idEdtItemName);

        // Setup insets if needed (this should not conflict with adjustResize if well tweaked)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the list and adapter
        lngList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);
        listvew.setAdapter(adapter);

        loadList();

        // Save index of clicked list item for editing
        listvew.setOnItemClickListener((parent, view, position, id) -> itemIndex = position);

        // Add button: add item from text entry then hide keyboard
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEdt.getText().toString().trim();
                if (!item.isEmpty()) {
                    lngList.add(item);
                    adapter.notifyDataSetChanged();
                    itemEdt.setText("");
                    saveList();
                    hideKeyboard();
                }
            }
        });

        // Delete button: delete item that matches text entry then hide keyboard
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToDelete = itemEdt.getText().toString().trim();
                if (!itemToDelete.isEmpty() && lngList.contains(itemToDelete)) {
                    lngList.remove(itemToDelete);
                    adapter.notifyDataSetChanged();
                    itemEdt.setText("");
                    saveList();
                    hideKeyboard();
                }
            }
        });

        // Edit button: replace selected item with new text then hide keyboard
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItemText = itemEdt.getText().toString().trim();
                if (!newItemText.isEmpty() && itemIndex != -1) {
                    lngList.set(itemIndex, newItemText);
                    adapter.notifyDataSetChanged();
                    itemEdt.setText("");
                    saveList();
                    hideKeyboard();
                }
            }
        });
    }

    // Save the list into SharedPreferences
    private void saveList() {
        SharedPreferences prefs = getSharedPreferences("ListPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Build a single string from all items
        StringBuilder sb = new StringBuilder();
        for (String item : lngList) {
            sb.append(item).append(DELIMITER);
        }
        editor.putString("listData", sb.toString());
        editor.apply();
    }

    // Load the list from SharedPreferences
    private void loadList() {
        SharedPreferences prefs = getSharedPreferences("ListPrefs", MODE_PRIVATE);
        String savedData = prefs.getString("listData", "");
        if (!savedData.isEmpty()) {
            String[] items = savedData.split(DELIMITER);
            for (String item : items) {
                if (!item.isEmpty()) {
                    lngList.add(item);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    // Helper method to hide the soft keyboard.
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveList();
    }
}
