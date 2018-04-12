package com.example.rohit.postvolley;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    EditText userName,emailID,GENder,AGe;
    Button post;                            //Worth less

    public static final String URL="http://192.168.0.110:9000/user/insert";
    public static  String macAddress="";
    public static  String uniqueCode="";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*uniqueCode = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        macAddress=uniqueCode;*/

        userName=findViewById(R.id.userName);
        emailID=findViewById(R.id.emaiId);
        GENder=findViewById(R.id.gender);
        AGe=findViewById(R.id.age);
        post=findViewById(R.id.post);

        //Log.d("This is unique code",m_androidId);

        //38a2b72651ae98d1
        //bc8f987979b7308e

        post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                registerUser();
            }
        });
    }

    public void registerUser()
    {
        final String uName=userName.getText().toString().trim();
        final String eMail=emailID.getText().toString().trim();
        final String gen=GENder.getText().toString().trim();
        final String ag=AGe.getText().toString().trim();

        uniqueCode = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        macAddress=uniqueCode;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error is :=-",""+error);
                    }
                })
        {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params=new HashMap<String,String>();
                params.put("name",uName);
                params.put("age",ag);
                params.put("gender",gen);
                params.put("emailId",eMail);
                params.put("macAddress",uniqueCode);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
