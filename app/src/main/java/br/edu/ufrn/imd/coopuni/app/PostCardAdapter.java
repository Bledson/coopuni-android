package br.edu.ufrn.imd.coopuni.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.parsers.ParsePost;


public class PostCardAdapter extends RecyclerView.Adapter<PostCardAdapter.ViewHolder> {

  List<Post> posts;
  private Context ctx;

  public PostCardAdapter(Context context) {
    super();
    ctx = context;
    posts = new ArrayList<Post>();
  }


  public void like(View view) {

  }

  public void fetch() {
    String JSON_URL = "http://10.0.0.104:8080/coopuni/rest/posts";
    StringRequest stringRequest = new StringRequest(JSON_URL,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                showJSON(response);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
            });
    int socketTimeout = 30000;
    RequestQueue requestQueue = Volley.newRequestQueue(ctx);
//    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//    stringRequest.setRetryPolicy(policy);
    requestQueue.add(stringRequest);
  }

  private void showJSON(String json){
    ParsePost pp = new ParsePost(json);
    posts = pp.parsePostsJSON();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    fetch();
    View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.card_post, viewGroup, false);
    ViewHolder viewHolder = new ViewHolder(v);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    Post post = posts.get(i);
    viewHolder.description.setText(post.getDescription());
    viewHolder.userName.setText(post.getUsername());
//    viewHolder.imgPost.setImageResource(Integer.parseInt(post.getPhoto()));
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgPost;
    public TextView description;
    public TextView userName;

    public ViewHolder(View itemView) {
      super(itemView);
//      imgPost = (ImageView) itemView.findViewById(R.id.img);
      description = (TextView) itemView.findViewById(R.id.description);
      userName = (TextView) itemView.findViewById(R.id.username);
    }
  }
}
