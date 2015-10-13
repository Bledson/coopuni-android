package br.edu.ufrn.imd.coopuni.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
  private static ArrayList<Post> posts;

  RecyclerView recyclerView;
  RecyclerView.LayoutManager layoutManager;
  RecyclerView.Adapter adapter;

  public ProfileFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);

    layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);

    RecyclerViewHeader header = RecyclerViewHeader.fromXml(getContext(), R.layout.header_profile);
    header.attachTo(recyclerView);

    adapter = new PostCardAdapter();
    recyclerView.setAdapter(adapter);
    return view;
  }
}
