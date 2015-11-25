package br.edu.ufrn.imd.coopuni.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.api.client.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    private EditText pwtxt;
    private EditText usertxt;
    private EditText emailtxt;
    private String url = "http://10.0.0.104:8080/coopuni/rest/members";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pwtxt = (EditText) findViewById(R.id.pwregister);
        usertxt = (EditText) findViewById(R.id.userregister);
        emailtxt = (EditText) findViewById(R.id.emailregister);
    }

    public void login(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private JSONObject createJsonObj() {
        JSONObject post = new JSONObject();
        String pw= pwtxt.getText().toString();
        String user = usertxt.getText().toString();
        String email = emailtxt.getText().toString();
        try {
            post.put("pw",pw);
            post.put("email",email);
            post.put("username",user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public void register(View v) {
        JSONObject postObject = this.createJsonObj();
        String url_ = "http://10.0.0.104:8080/coopuni/rest/members";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }



    private void openLogin() {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }



}
