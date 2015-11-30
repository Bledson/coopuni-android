package br.edu.ufrn.imd.coopuni.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufrn.imd.coopuni.model.Post;

public class CommentActivity extends AppCompatActivity {

    private Post postobj;
    private EditText content;
    private String ip = "http://10.3.129.150:8080/";
    private TextView username;
    private TextView category;
    private TextView area;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Intent i = getIntent();
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        postobj = (Post) b.getSerializable("post");

        username = (TextView)findViewById(R.id.username);
        //username.setText(postobj.getUsername());

        category = (TextView) findViewById(R.id.categorytxt);
        category.setText(postobj.getCategory());

        description = (TextView) findViewById(R.id.description);
        description.setText(postobj.getDescription());

        area = (TextView) findViewById(R.id.areatxt);
        area.setText(postobj.getArea());


        content = (EditText) findViewById(R.id.editText);
        content.requestFocus();


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
        long postid = postobj.getId();


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
        String url = ip+"coopuni/rest/comment";
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
