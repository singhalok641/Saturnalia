package in.exun.brinjal.saturnalia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import in.exun.brinjal.saturnalia.R;

public class Events extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View headerView;
    private int selection = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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
                    case R.id.event:
                        return true;
                    case R.id.schedule:
                        displayView(2);
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
    }

    private void displayView(int pos) {
        selection = pos;
        Intent i;
        switch (pos) {


            case 0:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;
            case 2:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;
            case 3:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;

            case 4:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;

            case 5:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;
            case 6:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;
            case 7:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;
            case 8:
                i = new Intent(this,MainActivity.class);
                i.putExtra("frag_id",pos);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }
    }



}
