package in.exun.brinjal.saturnalia.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import in.exun.brinjal.saturnalia.Adapters.EventsDetailsViewPagerAdapter;
import in.exun.brinjal.saturnalia.R;
import in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.Eligibility;
import in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.Judging;
import in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.Overview;
import in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.Prizes;
import in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.Rules;
import in.exun.brinjal.saturnalia.helper.AppConstants;
import in.exun.brinjal.saturnalia.helper.SQLiteHandler;

public class EventDetails extends AppCompatActivity {

    // Tag
    public static final String TAG = "EventDetails";
    public static int eid;
    private ViewPager viewPager;
    private ImageView htabView;
    TabLayout tabLayout;
    private Cursor eventDetailsCursor;
    private Cursor eventHeaderCursor;
    private SQLiteHandler db;
    private String headerImage;
    private int resID;
    // UI elements
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        

        // SQLiteHandler
        db = SQLiteHandler.getInstance(this);

        Bundle args = getIntent().getExtras();
        eid = args.getInt("event_id");
        Log.d(TAG, "onCreate: " + eid);

        String where = "id = '" + eid + "'";
        eventDetailsCursor = db.fetchByCondition(AppConstants.TABLE_EVENTS,where);
        Log.d(TAG, getStringData("Eligibility"));
        // Bundle details = new Bundle();
        // details.putString("Eligibility",getStringData("Eligibility"));
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getStringData("Event_Name"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    private void setupViewPager(ViewPager viewPager) {
        EventsDetailsViewPagerAdapter adapter = new EventsDetailsViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Overview(), "Overview");
        adapter.addFrag(new Eligibility(), "Eligibility");
        adapter.addFrag(new Judging(), "Judging Criteria");
        adapter.addFrag(new Rules(), "Rules");
        adapter.addFrag(new Prizes(), "Prizes");

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String where = "id = '" + eid + "'";
        eventDetailsCursor = db.fetchByCondition(AppConstants.TABLE_EVENTS,where);

        setToolbar();

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        //TabLayout
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.htab_tabs);

        htabView = (ImageView)findViewById(R.id.htab_header);
        headerImage = getStringData("header_image");
        resID = getResources().getIdentifier(headerImage , "drawable", getPackageName());

        bitmap = BitmapFactory.decodeResource(getResources(),resID);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener(){
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimaryDark);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(vibrantDarkColor);
            }
        });

        htabView.setImageResource(resID);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {
                                                   viewPager.setCurrentItem(tab.getPosition());

                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {

                                               }

                                           }
        );

    }

    //for the arrow button which takes backwards
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public int getIntData(String colName){

        return  eventDetailsCursor.getInt(eventDetailsCursor.getColumnIndexOrThrow(colName));
    }

    public String getStringData(String colName){

        return  eventDetailsCursor.getString(eventDetailsCursor.getColumnIndexOrThrow(colName));
    }



}
