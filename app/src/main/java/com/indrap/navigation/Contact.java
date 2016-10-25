package com.indrap.navigation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Contact extends Fragment {
    public Contact(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RelativeLayout) inflater.inflate(R.layout.activity_contact, container, false);

        //getActivity().setTitle("Categories");
        //getActivity().set(toolbar);
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Categories");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorcategories1));

        LinearLayout linear = (LinearLayout)getActivity().findViewById(R.id.nav_head);
        linear.setBackgroundColor(getResources().getColor(R.color.colorcategories1));
        return view;
    }
}
