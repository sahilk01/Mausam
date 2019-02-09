package com.elgigs.sahilweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class search extends AppCompatActivity {
    EditText getSearch;
    String enteredSearch;
    TextView doSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSearch = findViewById(R.id.get_search);
        doSearch = findViewById(R.id.search_btn);
        doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredSearch = getSearch.getText().toString().toLowerCase().trim();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("forsearch", enteredSearch);
                startActivity(intent);
            }
        });

    }
}
