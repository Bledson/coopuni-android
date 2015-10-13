package br.edu.ufrn.imd.coopuni.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
  private TabLayout tabLayout;
  private Toolbar toolbar;
  private ViewPager viewPager;

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
  }

  private void setupTabIcons() {
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_comment);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_like);
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
