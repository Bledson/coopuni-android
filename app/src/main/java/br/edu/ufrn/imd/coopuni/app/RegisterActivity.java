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



public class RegisterActivity extends AppCompatActivity {


    private EditText pwtxt;
    private EditText usertxt;
    private EditText emailtxt;
    private String url = "http://10.3.129.150:8080/coopuni/rest/members";

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
        JSONObject user = new JSONObject();
        String pw= pwtxt.getText().toString();
        String username = usertxt.getText().toString();
        String email = emailtxt.getText().toString();
        try {
            user.put("pw",pw);
            user.put("email",email);
            user.put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void register(View v) {
        JSONObject userObject = this.createJsonObj();
        String url_ = "http://10.0.0.104:8080/coopuni/rest/members";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, userObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                openLogin();
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
