package br.edu.ufrn.imd.coopuni.app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import br.edu.ufrn.imd.coopuni.request.OAuthTokenRequest;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

  private TabLayout tabLayout;
  private Toolbar toolbar;
  private ViewPager viewPager;
  private FloatingActionButton fab;
  private String jsonResponse;
  private TextView username;
  private String ip = "http://10.0.0.104:8080/";
  private String url = ip+"coopuni/rest/members/login/";


  public void openRegisterPost(View view) {
    Intent intent = new Intent(this, RegisterPostActivity.class);
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    viewPager = (ViewPager) findViewById(R.id.viewPager);
    setupViewPager(viewPager);

    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    setupTabIcons();
    username = (TextView) findViewById(R.id.username);

    fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext
        (), R.color.accent)));
  }

  private void setupTabIcons() {
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_account);
  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    viewPagerAdapter.addFragment(new HomeFragment(), "");
    viewPagerAdapter.addFragment(new ProfileFragment(), "");
//    viewPagerAdapter.addFragment(, "Status");
    viewPager.setAdapter(viewPagerAdapter);
  }
}
