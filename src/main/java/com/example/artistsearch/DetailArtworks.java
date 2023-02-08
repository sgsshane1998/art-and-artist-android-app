package com.example.artistsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailArtworks extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int[] icons = new int[] {
            R.drawable.detail_icon,R.drawable.artwork_icon
    };

    private List<String> options;
    private List<String> data;

    private List<Artwork> artworkList;
    private ProgressBar de_ar_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artworks);

        toolbar = findViewById(R.id.da_bar);
        setSupportActionBar(toolbar);
        de_ar_pro = findViewById(R.id.de_ar_pro);
        de_ar_pro.setVisibility(View.INVISIBLE);


        options = new ArrayList<>();
        data = new ArrayList<>();
        artworkList = new ArrayList<>();
       // tabLayout = findViewById(R.id.tabLayout);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String id = extras.getString("id");
        String name = extras.getString("name");
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://10.0.2.2:8080/detail?id=" + id;
        String url = "https://csci571-131415.wl.r.appspot.com/detail?id=" + id;
        de_ar_pro.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        de_ar_pro.setVisibility(View.INVISIBLE);
                        try {
                            if (!response.getString("name").equals("")) {
                                options.add("name");
                                data.add(response.getString("name"));
                            }
                            if (!response.getString("birthday").equals("")) {
                                options.add("birthday");
                                data.add(response.getString("birthday"));
                            }
                            if (!response.getString("deathday").equals("")) {
                                options.add("deathday");
                                data.add(response.getString("deathday"));
                            }
                            if (!response.getString("nationality").equals("")) {
                                options.add("nationality");
                                data.add(response.getString("nationality"));
                            }
                            if (!response.getString("biography").equals("")) {
                                options.add("biography");
                                data.add(response.getString("biography"));
                            }

                            tabLayout = (TabLayout) findViewById(R.id.tabLayout);



                            viewPager = (ViewPager) findViewById(R.id.view_pager);
                            adapter = new ViewPagerAdapter(getSupportFragmentManager());


                            adapter.addFragment(new DetailFrag(options, data));
                            //adapter.addFragment(new ArtworkFrag());
                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);
                            tabLayout.getTabAt(0).setIcon(icons[0]);
                            tabLayout.getTabAt(0).setText("DETAILS");
                            /*tabLayout.getTabAt(1).setIcon(icons[1]);
                            tabLayout.getTabAt(1).setText("ARTWORK");*/
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
        queue.add(jsonObjectRequest);

///////////////////////////////////////////////////////////////////////////


        RequestQueue queue_2 = Volley.newRequestQueue(this);
        //String url_2 = "http://10.0.2.2:8080/artworks?id=" + id;
        String url_2 = "https://csci571-131415.wl.r.appspot.com/artworks?id=" + id;
        JsonObjectRequest jsonObjectRequest_2 = new JsonObjectRequest
                (Request.Method.GET, url_2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray artworks;
                        try {
                            artworks = response.getJSONObject("_embedded").getJSONArray("artworks");
                            for (int i = 0; i < artworks.length(); i++) {
                                String id = artworks.getJSONObject(i).getString("id");
                                Log.d("url2_id", id);
                                String title = artworks.getJSONObject(i).getString("title");
                                String link = artworks.getJSONObject(i).getJSONObject("_links").getJSONObject("thumbnail").getString("href");
                                artworkList.add(new Artwork(id, title, link));
                            }

                            viewPager = (ViewPager) findViewById(R.id.view_pager);

                            adapter.addFragment(new ArtworkFrag(artworkList));

                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);

                            tabLayout.getTabAt(0).setIcon(icons[0]);
                            tabLayout.getTabAt(0).setText("DETAILS");
                            tabLayout.getTabAt(1).setIcon(icons[1]);
                            tabLayout.getTabAt(1).setText("ARTWORK");

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
        queue.add(jsonObjectRequest_2);









    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}