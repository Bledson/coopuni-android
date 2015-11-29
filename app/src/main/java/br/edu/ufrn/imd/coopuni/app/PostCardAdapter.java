package br.edu.ufrn.imd.coopuni.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.parsers.ParsePost;


public class PostCardAdapter extends RecyclerView.Adapter<PostCardAdapter.ViewHolder> {

    List<Post> posts;
    private String ip = "http://192.168.0.110:8080/";

    public PostCardAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Post post = posts.get(i);
        viewHolder.description.setText(post.getDescription());
        viewHolder.userName.setText(post.getUsername());
        viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = post.getId();
                String url = ip + "coopuni/rest/post/like/";
                fetchvote(v, id, url);
            }
        });
//    viewHolder.imgPost.setImageResource(Integer.parseInt(post.getPhoto()));
    }

    private void fetchvote(View v, final long id, String url) {


        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id", String.valueOf(id));
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        queue.add(putRequest);

    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (posts != null)
            itemCount = posts.size();
        return itemCount;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgPost;
        public TextView description;
        public TextView userName;
        public ImageButton btnLike;

        public ViewHolder(View itemView) {
            super(itemView);
//      imgPost = (ImageView) itemView.findViewById(R.id.img);
            btnLike = (ImageButton) itemView.findViewById(R.id.likeButton);
            description = (TextView) itemView.findViewById(R.id.description);
            userName = (TextView) itemView.findViewById(R.id.username);
        }
    }
}
