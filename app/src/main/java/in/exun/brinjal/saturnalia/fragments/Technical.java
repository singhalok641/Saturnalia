package in.exun.brinjal.saturnalia.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.exun.brinjal.saturnalia.Adapters.EventAdapter;
import in.exun.brinjal.saturnalia.R;
import in.exun.brinjal.saturnalia.activity.EventDetails;
import in.exun.brinjal.saturnalia.helper.AppConstants;
import in.exun.brinjal.saturnalia.helper.SQLiteHandler;


public class Technical extends Fragment {

    private static final String TAG = "Technical";
    private View rootView;
    private Cursor eventsList;
    private RecyclerView recyclerView;
    private SQLiteHandler db;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_events, container, false);

        // SQLiteHandler
        db = SQLiteHandler.getInstance(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        populateEventsList();


        return rootView;


    }

    private void populateEventsList() {

        String where = "Event_Type = '2'";
        eventsList = db.fetchByCondition(AppConstants.TABLE_EVENTS,where);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventAdapter(getActivity(), eventsList);
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();

        ((EventAdapter)mAdapter).setOnItemClickListener(new EventAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Intent i =new Intent(getActivity(), EventDetails.class);
                i.putExtra("event_id",position);
                getActivity().startActivity(i);
            }
        });

    }

}


