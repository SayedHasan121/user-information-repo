package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edname,ednumber,edgmail,edaddress;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        edname=findViewById(R.id.edname);
        ednumber=findViewById(R.id.ednumber);
        edgmail=findViewById(R.id.edgmail);
        edaddress=findViewById(R.id.edaddress);
        progressbar=findViewById(R.id.progressbar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressbar.setVisibility(View.VISIBLE);



                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://speechbd.000webhostapp.com/apps/info.json";


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressbar.setVisibility(View.GONE);
                                Log.d("serverRes",response);

                                try {
                                    JSONObject jsonObject=new JSONObject(response);

                                    String name=jsonObject.getString("name");
                                    String mobile=jsonObject.getString("mobile");
                                    String gmail=jsonObject.getString("gmail");
                                    String address=jsonObject.getString("address");


                                    edname.setText(name);
                                    ednumber.setText(mobile);
                                    edgmail.setText(gmail);
                                    edaddress.setText(address);



                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        button.setText("That didn't work!");
                        progressbar.setVisibility(View.GONE);


                    }
                });

                queue.add(stringRequest);



            }
        });


    }
}