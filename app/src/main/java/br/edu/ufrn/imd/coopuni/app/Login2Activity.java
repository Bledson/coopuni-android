package br.edu.ufrn.imd.coopuni.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufrn.imd.coopuni.request.AppController;

public class Login2Activity extends AppCompatActivity {


    private String url = "http://10.0.0.104:8080/coopuni/rest/members/login/";
    private String urlrequest;
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

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

    }

    public void logar(View v) {
        String pw = txtpw.getText().toString();
        String username = txtusername.getText().toString();
        urlrequest = url + username + "/" + pw;
        makeJsonArrayRequest();
        if(authorized(result)) {
            Intent i = new Intent(this,MainActivity.class);
            this.startActivity(i);
        } else {
            Toast.makeText(Login2Activity.this, "Login incorreto", Toast.LENGTH_SHORT).show();
        }
        urlrequest = null;
    }

    private void makeJsonArrayRequest() {

        showpDialog();

        StringRequest strReq = new StringRequest(Request.Method.GET, urlrequest, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                result = response;
                hidepDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidepDialog();
            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }





    private boolean authorized(String response) {
        if(response != null)
            return true;
        return false;
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
