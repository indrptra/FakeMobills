package com.indrap.navigation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mycomputer on 13/10/16.
 */

public class Root extends Fragment{
    public Root(){}
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_home, container, false);

        getActivity().setTitle("Dashboard");
        setHasOptionsMenu(true);
        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Dashboard/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    /*public Root(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (RelativeLayout) inflater.inflate(R.layout.activity_home, container, false);

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colordashboard));
        LinearLayout linear = (LinearLayout)getActivity().findViewById(R.id.nav_head);
      //  linear.setBackgroundColor(getResources().getColor(R.color.colordashboard));
        return view;
    }*/
}
