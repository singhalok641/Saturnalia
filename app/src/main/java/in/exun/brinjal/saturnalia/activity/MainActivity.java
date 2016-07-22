package in.exun.brinjal.saturnalia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import in.exun.brinjal.saturnalia.R;
import in.exun.brinjal.saturnalia.fragments.Home;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View headerView;
    private int selection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle args = getIntent().getExtras();
        if (args != null){
            selection = args.getInt("frag_id");
        }

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialising NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        headerView = navigationView.inflateHeaderView(R.layout.nav_header);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                menuItem.setChecked(true);

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.home:
                        displayView(0);
                        return true;
                    case R.id.schedule:
                        displayView(2);
                        return true;
                    case R.id.event:
                        displayView(1);
                        return true;
                    case R.id.workshops:
                        displayView(3);
                        return true;
                    case R.id.lectures:
                        displayView(4);
                        return true;
                    case R.id.sponsors:
                        displayView(5);
                        return true;

                    case R.id.setting:
                        displayView(6);
                        return true;

                    case R.id.about:
                        displayView(7);
                        return true;

                    case R.id.faq:
                        displayView(8);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }


            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        displayView(selection);
    }

    private void displayView(int pos) {
        Fragment fragment = null;
        String title = "Brinjal";
        selection = pos;
        Log.d(TAG, "displayView: " + pos);
        Intent i;
        switch (pos) {

            case 0:
                title = "Home";
                fragment=new Home();
                break;
            case 1:
                i = new Intent(this,Events.class);
                startActivity(i);
                finish();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;

            case 5:
                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(MainActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(MainActivity.this, "FAQ", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        if (fragment!=null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);

            getSupportActionBar().setTitle(title);
            fragmentTransaction.commit();
        }
    }


}

