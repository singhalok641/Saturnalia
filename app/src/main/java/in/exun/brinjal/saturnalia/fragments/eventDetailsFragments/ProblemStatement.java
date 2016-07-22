package in.exun.brinjal.saturnalia.fragments.eventDetailsFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.exun.brinjal.saturnalia.R;
import in.exun.brinjal.saturnalia.activity.EventDetails;
import in.exun.brinjal.saturnalia.helper.AppConstants;
import in.exun.brinjal.saturnalia.helper.SQLiteHandler;

/**
 * Created by root on 6/21/16.
 */
public class ProblemStatement extends Fragment {
    private SQLiteHandler db;
    private Cursor ProblemStatementDetails;

    //TAG
    public static final String PROVIDER_NAME ="in.exun.brinjal.saturnalia.fragments.eventDetailsFragments.";
    TextView detailsTV;
    int eid;
    String data;

    private View rootView;

    public ProblemStatement()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        eid = EventDetails.eid;
        rootView = inflater.inflate(R.layout.list_row_event_details, container, false);
        detailsTV = (TextView) rootView.findViewById(R.id.detailsTV);
        db = SQLiteHandler.getInstance(getActivity().getApplicationContext());
        String extra = "";
        Log.d("TAG", String.valueOf(eid));

        String where = "id = '" + eid + "'";
        ProblemStatementDetails = db.fetchByCondition(AppConstants.TABLE_EVENTS,where);


        data = getStringData("Rules");

        detailsTV.setText(Html.fromHtml(data));
        return rootView;
    }

    public String getStringData(String colName){

        return  ProblemStatementDetails.getString(ProblemStatementDetails.getColumnIndexOrThrow(colName));
    }
}
