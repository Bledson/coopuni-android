package br.edu.ufrn.imd.coopuni.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ufrn.imd.coopuni.model.Post;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static ArrayList<Post> posts = new ArrayList<Post>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

//    layoutManager = new LinearLayoutManager(getContext());
//    recyclerView.setLayoutManager(layoutManager);

//    adapter = new PostCardAdapter(this.getActivity());
//    recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostCardAdapter(posts);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://10.3.129.150:8080/coopuni/rest/posts";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.length() > 0) {
                        posts.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Post post = new Post();
                            if (!jsonObject.isNull("description")) {
                                post.setDescription(jsonObject.getString("description"));;
                            }
                            if (!jsonObject.isNull("username")) {
                                post.setDescription(jsonObject.getString("username"));;
                            }
                            posts.add(i, post);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something
            }
        });

        requestQueue.add(jsonArrayRequest);

        return view;
    }

    public void like(View view) {
        //
    }

    public void downvote() {
        //
    }

    public void comment() {
        //
    }

    public void share() {
        //
    }
}
