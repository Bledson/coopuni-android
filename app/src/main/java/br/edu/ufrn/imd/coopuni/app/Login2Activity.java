package br.edu.ufrn.imd.coopuni.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import br.edu.ufrn.imd.coopuni.request.OAuthTokenRequest;

public class Login2Activity extends AppCompatActivity {


    private String url = "http://10.0.0.104:8080/coopuni/rest/members/login/";
    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnLogin;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtusername;
    private TextView txtpw;
    private String result;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtpw = (TextView) findViewById(R.id.pwtxt);
        txtusername = (TextView) findViewById(R.id.usernametxt);
    }


    public void logar(View v) {
//        String user = txtusername.getText().toString();
//        String pw = txtpw.getText().toString();
//        String LOGIN_URL = url + user + "/" + pw;
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOGIN_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response != null){
//                            openHome();
//                        }else {
//                            Toast.makeText(Login2Activity.this, response, Toast.LENGTH_LONG).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Login2Activity.this,error.toString(),Toast.LENGTH_LONG ).show();
//                    }
//                }){
//
//        };
//        int socketTimeout = 30000;
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
        openHome();
    }

    private void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public void logarsigaa(View v) {
        Intent i = new Intent(this, MainActivity.class);
        OAuthTokenRequest.getInstance().
                getTokenCredential(this,
                        "http://apitestes.info.ufrn.br/authz-server","coop-uni-id",
                        "coopuni", i);
    }

}
