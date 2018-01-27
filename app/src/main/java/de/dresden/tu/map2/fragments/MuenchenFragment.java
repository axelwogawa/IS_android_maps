package de.dresden.tu.map2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.dresden.tu.map2.R;

/**
 * Created by Dennis Klar on 27.01.2018.
 */

public class MuenchenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_muenchen,container,false);
    }
}
