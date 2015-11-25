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
        Post post = posts.get(i);
        viewHolder.description.setText(post.getDescription());
        viewHolder.userName.setText(post.getUsername());
//    viewHolder.imgPost.setImageResource(Integer.parseInt(post.getPhoto()));
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

        public ViewHolder(View itemView) {
            super(itemView);
//      imgPost = (ImageView) itemView.findViewById(R.id.img);
            description = (TextView) itemView.findViewById(R.id.description);
            userName = (TextView) itemView.findViewById(R.id.username);
        }
    }
}
