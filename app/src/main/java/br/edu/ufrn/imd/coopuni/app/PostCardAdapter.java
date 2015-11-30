package br.edu.ufrn.imd.coopuni.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import br.edu.ufrn.imd.coopuni.model.Post;



public class PostCardAdapter extends RecyclerView.Adapter<PostCardAdapter.ViewHolder> {

    List<Post> posts;
    private String ip = "http://10.3.129.150:8080/";

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
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Post post = posts.get(i);
        viewHolder.description.setText(post.getDescription());
        viewHolder.userName.setText(post.getUsername());
        viewHolder.downs.setText(String.valueOf(post.getDownvotes()));
        viewHolder.likes.setText(String.valueOf(post.getLikes()));
        viewHolder.userName.setText(post.getUsername());
        viewHolder.category.setText(post.getCategory());
        viewHolder.area.setText(post.getArea());
        viewHolder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("post",post);
                i.putExtras(bundle);
                v.getContext().startActivity(i);
            }
        });
        viewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = viewHolder.likes.getText().toString();
                int new_value = Integer.valueOf(value) + 1;
                viewHolder.likes.setText(String.valueOf(new_value));
                long id = post.getId();
                viewHolder.btnLike.setEnabled(false);
                viewHolder.btnDown.setEnabled(false);
                String url = ip + "coopuni/rest/posts/like/";
                fetchvote(v, id, url);
            }
        });
        viewHolder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = viewHolder.downs.getText().toString();
                int new_value = Integer.valueOf(value) + 1;
                viewHolder.downs.setText(String.valueOf(new_value));
                long id = post.getId();
                viewHolder.btnLike.setEnabled(false);
                viewHolder.btnDown.setEnabled(false);
                String url = ip + "coopuni/rest/posts/down/";
                fetchvote(v, id, url);
            }
        });
//    viewHolder.imgPost.setImageResource(Integer.parseInt(post.getPhoto()));
    }

    private void fetchvote(View v, final long id, String url) {

        url = url + id;
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
        );
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
        public TextView category;
        public TextView area;
        public ToggleButton btnLike;
        public ToggleButton btnDown;
        public TextView likes;
        public TextView downs;
        public TextView username;
        public ImageButton comment;

        public ViewHolder(View itemView) {
            super(itemView);
            comment = (ImageButton) itemView.findViewById(R.id.comment);
            area = (TextView) itemView.findViewById(R.id.areatxt);
            category =  (TextView) itemView.findViewById(R.id.categorytxt);
            username = (TextView) itemView.findViewById(R.id.username);
            imgPost = (ImageView) itemView.findViewById(R.id.img);
            likes = (TextView) itemView.findViewById(R.id.likesTxt);
            btnDown = (ToggleButton) itemView.findViewById(R.id.downvote);
            downs = (TextView) itemView.findViewById(R.id.downvoteTxt);
            btnLike = (ToggleButton) itemView.findViewById(R.id.likeButton);
            description = (TextView) itemView.findViewById(R.id.description);
            userName = (TextView) itemView.findViewById(R.id.username);
        }
    }
}
