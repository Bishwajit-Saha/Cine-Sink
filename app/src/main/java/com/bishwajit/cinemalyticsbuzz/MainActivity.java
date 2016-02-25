package com.bishwajit.cinemalyticsbuzz;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    String url_gen="";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    RequestQueue rq = null;
    ArrayList<MovieModel> movielist = null;
    TabLayout tabLayout= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_search)
        {
//            Toast.makeText(getApplicationContext(), "got ur press", Toast.LENGTH_SHORT).show();

            final EditText searchBar = (EditText)findViewById(R.id.search_bar);
            searchBar.setVisibility(View.VISIBLE);
            findViewById(R.id.action_search).setVisibility(View.GONE);
/*
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
            searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        String search=searchBar.getText().toString();
                        searchBar.setVisibility(View.GONE);
                        findViewById(R.id.action_search).setVisibility(View.VISIBLE);
                        generateUrl(search);
                        handled = true;
                    }
                    return handled;
                }
            });
            return true;
        }
        if(id == R.id.action_refresh)
        {
            int tab =tabLayout.getSelectedTabPosition();
            //Toast.makeText(getApplicationContext(),Integer.toString(tab), Toast.LENGTH_SHORT).show();
            generateUrl("");
            return true;
        }

//        return super.onOptionsItemSelected(item);
        return true;
    }

    // generates the url to b fetched
    public void generateUrl(String search)
    {
        int i= tabLayout.getSelectedTabPosition();
        switch(i)
        {
            case 0:
                UrlBuilder b = new UrlBuilder(search);
                url_gen = b.getUrl();
//                 Toast.makeText(getApplicationContext(), url_gen, Toast.LENGTH_LONG).show();
                break;
            case 1:
                url_gen= "http://api.cinemalytics.com/v1/movie/releasedthisweek?auth_token=6843156245E0AD2B4A7F64D6EFDD1D5C";
                break;
            case 2:
                url_gen = "http://api.cinemalytics.com/v1/analytics/TopMovies/?auth_token=6843156245E0AD2B4A7F64D6EFDD1D5C";
                break;
        }
        fetchData();
    }

    public void fetchData()
    {
        rq = VolleySingleton.getsInstance().getmRequestQueue();
        StringRequest request = new StringRequest( Request.Method.GET,url_gen,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                      Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        JsonToArray joa = new JsonToArray(response);
                        movielist = joa.getArray();
                        // call the string to movie array maker here.
                      //  showData();
                       /* StringBuilder sb = new StringBuilder();
                        MovieModel m;
                        for(int i =0; i<movielist.size();i++)
                        {
                            m = movielist.get(i);
                            sb.append(m.getOriginalTitle() + "\n");
                        }
                        String s = sb.toString();
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        Log.e("Response", s);*/


                        showData();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
        rq.add(request);
    }

    public void showData()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.Adapter adapter = new RecyclerAdapter(movielist,this);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_search, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            //showData(rootView);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Search";
                case 1:
                    return "Upcoming";
                case 2:
                    return "Top 10";
            }
            return null;
        }
    }


}
