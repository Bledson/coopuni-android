package br.edu.ufrn.imd.coopuni.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.api.client.json.Json;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPostActivity extends AppCompatActivity {
  private static final int FOTO = 1;
  private ImageButton fotoBtn;
  private Spinner areaSpinner;
  private Spinner categorySpinner;
  private EditText description;
  private RadioGroup radioGroup;
  private String ip = "http://10.3.129.150:8080/";

  public void openRegisterComment(View view) {
    Intent intent = new Intent(this, CommentActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register_post);

    fotoBtn = (ImageButton) findViewById(R.id.fotoBtn);
    fotoBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, FOTO);
      }
    });

    categorySpinner = (Spinner) findViewById(R.id.categoriaSpinner);
    areaSpinner = (Spinner) findViewById(R.id.areaSpinner);
    description = (EditText) findViewById(R.id.descriptionTxt);
    radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.areas_array, android.R.layout.simple_spinner_item);

    ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this,
            R.array.categories_array, android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    areaSpinner.setAdapter(adapter);
    categorySpinner.setAdapter(adapterCategory);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode==FOTO) {
      Bitmap foto = (Bitmap) data.getExtras().get("data");

      fotoBtn.setImageBitmap(foto);
    }
  }

  private int choosetype(){
    int typeid = radioGroup.getCheckedRadioButtonId();
    if(typeid == R.id.denunciaBtn)
      typeid = 1;
    else
      typeid= 2;
    return typeid;
  }

  private int choosecategory() {
    return categorySpinner.getSelectedItemPosition() + 1;
  }

  private int chooseArea() {
    return areaSpinner.getSelectedItemPosition() + 1;

  }

  private int getUser() {
    return 4;
  }

  private JSONObject createJsonObj() {
    JSONObject post = new JSONObject();
    JSONObject category = new JSONObject();
    JSONObject area = new JSONObject();
    JSONObject member = new JSONObject();
    //JSONObject geolocation = new JSONObject();

    String desc = description.getText().toString();
    int typeid =  choosetype();
    int categoryid = choosecategory();
    int areaid = chooseArea();
    int userid = getUser();


    try {
      category.put("id",categoryid);
      area.put("id",areaid);
      member.put("id",userid);

      post.put("description", desc);
      post.put("category", category);
      post.put("area",area);
      post.put("type",typeid);
      post.put("member",member);
    }catch (JSONException e) {

    }
    return post;
  }

  private void openHome() {
    Intent i = new Intent(this,MainActivity.class);
    startActivity(i);

  }

  public void registerPost(View v) {
    JSONObject postObject = this.createJsonObj();
    String url = ip+"coopuni/rest/posts";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postObject,
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
    openHome();
  }


}
