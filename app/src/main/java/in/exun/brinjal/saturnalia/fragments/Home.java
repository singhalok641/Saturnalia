package in.exun.brinjal.saturnalia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.exun.brinjal.saturnalia.R;

/**
 * Created by ayush on 09/06/16.
 */
public class Home extends Fragment {


    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }
}
