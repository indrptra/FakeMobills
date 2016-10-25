package com.indrap.navigation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by mycomputer on 13/10/16.
 */

public class Dashboard extends Fragment{
    public Dashboard(){}
    RelativeLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (RelativeLayout) inflater.inflate(R.layout.activity_home, container, false);

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colordashboard));
        LinearLayout linear = (LinearLayout)getActivity().findViewById(R.id.nav_head);
        linear.setBackgroundColor(getResources().getColor(R.color.colordashboard));
        setHasOptionsMenu(true);
        return view;
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
}
