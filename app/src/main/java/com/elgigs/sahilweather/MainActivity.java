package com.elgigs.sahilweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
        TextView city_t, date_t, temp_t, description_t;
        Button search;
        String Fsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city_t = findViewById(R.id.city);
        date_t = findViewById(R.id.date);
        temp_t = findViewById(R.id.temp);
        description_t = findViewById(R.id.weather_type);
        Fsearch = getIntent().getStringExtra("forsearch");
                findweather();
    }

    private void findweather() {
//        String cityToSearch = Fsearch;
//        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=london,uk&appid=50f9ce094d9427636c0fe09dff85064f&units=imperial";
        String query = "https://api.openweathermap.org/data/2.5/weather?q="+Fsearch+"&appid=50f9ce094d9427636c0fe09dff85064f&units=imperial";
        String url = query;
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");


                    city_t.setText(city);
                    description_t.setText(description);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE-MM-DD");
                    String formatted_date = sdf.format(calendar.getTime());

                    date_t.setText(formatted_date);

                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 32) /1.8000;
                    centi = Math.round(centi);
                    int i = (int)centi;
                    temp_t.setText(String.valueOf(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }

    public void searchmore(View view) {
        Intent searchmor = new Intent(getApplicationContext(), search.class);
        startActivity(searchmor);
        finish();
    }
}
