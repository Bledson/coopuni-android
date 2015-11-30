package br.edu.ufrn.imd.coopuni.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentActivity extends AppCompatActivity {

    private Long postID;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        content = (EditText) findViewById(R.id.editText);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        postID = sharedPref.getLong("post_id", 22);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private JSONObject createJsonObj() {
        JSONObject comment = new JSONObject();
        JSONObject member = new JSONObject();
        JSONObject post = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject area = new JSONObject();

        String content = this.content.getText().toString();
        int userid = 1;
        int postid = 12;


        try {
            member.put("id", userid);
            post.put("id", postid);

            comment.put("content", content);
            comment.put("member", member);
            comment.put("post", post);
        } catch (JSONException e) {

        }
        return comment;
    }

    public void registerComment(View view) {
        JSONObject commentObject = this.createJsonObj();
        String url = "http://192.168.0.10:8080/coopuni/rest/comment";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, commentObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("resposta", jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }
}
