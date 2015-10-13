package br.edu.ufrn.imd.coopuni.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PostCardAdapter extends RecyclerView.Adapter<PostCardAdapter.ViewHolder> {

  List<Post> mItems;

  public PostCardAdapter() {
    super();
    mItems = new ArrayList<Post>();
    Post post = new Post();
    post.setDescription("The Great Barrier Reef 1");
    post.setUsername("andrezap");
    post.setPhoto(String.valueOf(R.drawable.ic_post));
    mItems.add(post);

    post = new Post();
    post.setUsername("andrezap");
    post.setDescription("The Great Barrier Reef 2 ");
    post.setPhoto(String.valueOf(R.drawable.ic_post));
    mItems.add(post);

    post = new Post();
    post.setUsername("andrezap");
    post.setDescription("The Great Barrier Reef 3");
    post.setPhoto(String.valueOf(R.drawable.ic_post));
    mItems.add(post);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View v = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.recycler_view_card_item, viewGroup, false);
    ViewHolder viewHolder = new ViewHolder(v);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    Post post = mItems.get(i);
    viewHolder.description.setText(post.getDescription());
    viewHolder.userName.setText(post.getUsername());
    viewHolder.imgPost.setImageResource(Integer.parseInt(post.getPhoto()));
  }

  @Override
  public int getItemCount() {
    return mItems.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgPost;
    public TextView description;
    public TextView userName;

    public ViewHolder(View itemView) {
      super(itemView);
      imgPost = (ImageView) itemView.findViewById(R.id.img);
      description = (TextView) itemView.findViewById(R.id.description);
      userName = (TextView) itemView.findViewById(R.id.username);
    }
  }
}
