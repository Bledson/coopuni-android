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
import com.google.api.client.json.Json;

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
    private String ip = "http://10.0.0.104:8080/";
    private String url = ip+"coopuni/rest/posts";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PostCardAdapter(posts);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());


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
                                post.setDescription(jsonObject.getString("description"));
                            }
                            String id = jsonObject.getString("id");
                            post.setId(Integer.valueOf(id));

                            String likes = jsonObject.getString("likes");
                            post.setLikes(Integer.valueOf(likes));

                            String down = jsonObject.getString("downvotes");
                            post.setDownvotes(Integer.valueOf(down));

                            JSONObject categoryobj = new JSONObject(jsonObject.getString("category"));
                            post.setCategory(categoryobj.getString("name"));

                            JSONObject areaobj = new JSONObject(jsonObject.getString("area"));
                            post.setArea(areaobj.getString("name"));

                            if (!jsonObject.isNull("member")) {
                                JSONObject memberobj = new JSONObject(jsonObject.getString("member"));
                                post.setDescription(memberobj.getString("username"));
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
}
