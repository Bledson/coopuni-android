package br.edu.ufrn.imd.coopuni.parses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufrn.imd.coopuni.app.Post;

/**
 * Created by andreza on 17/11/15.
 */
public class ParsePost {

    private List<Post> listpost;
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_MEMBER = "member";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_TYPE = "type";
    public static final String KEY_AREA = "area";
    public static final String KEY_NAME = "name";

    private String json;

    public ParsePost(String json){
        this.json = json;
    }

    public Post createpost(String username, String description, String type, String category,
        String area){
        Post post = new Post(category,type,description,0,0,username);
        return post;
    }

    public List<Post> parsePostsJSON(){
        try {
            listpost = new ArrayList<Post>();
            JSONArray posts = new JSONArray(json);

            for(int i=0;i<posts.length();i++){
                JSONObject post = posts.getJSONObject(i);

                JSONObject member = post.getJSONObject(KEY_MEMBER);
                String membername = member.getString(KEY_NAME);

                String description = post.getString(KEY_DESCRIPTION);

                String type = post.getString(KEY_TYPE);

                JSONObject category = post.getJSONObject(KEY_CATEGORY);
                String categoryname = category.getString(KEY_NAME);

                JSONObject area = post.getJSONObject(KEY_AREA);
                String areaname = area.getString(KEY_NAME);

                Post p = createpost(membername,description,type,categoryname,areaname);
                listpost.add(p);
            }
            return listpost;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
