package br.edu.ufrn.imd.coopuni.app;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
        pwtxt = (EditText) findViewById(R.id.userregister);
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

    public void register(View v) {
        String pw = pwtxt.getText().toString();
        String user = usertxt.getText().toString();
        String email = emailtxt.getText().toString();
        try {
            JSONObject obj = new JSONObject();
            obj.put("username",user);
            obj.put("pw",pw);
            obj.put("email",email);
        }catch (JSONException e) {

        }

    }

    private void openLogin() {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    private void registerUser(){
        final String pw = pwtxt.getText().toString();
        final String user = usertxt.getText().toString();
        final String email = emailtxt.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        openLogin();
                        Toast.makeText(RegisterActivity.this,"registrado", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",user);
                params.put("pw",pw);
                params.put("email", email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
