package com.example.artistsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class searchable extends AppCompatActivity implements ArtistRecyclerAdapter.OnArtistListener {
    private TextView no_artist;
    private Toolbar toolbar;
    private ArrayList<ArtistCard> artists;
    private RecyclerView recyclerView;
    private ProgressBar artist_pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        artist_pro = findViewById(R.id.pro_artist);
        artists = new ArrayList<>();
        toolbar = findViewById(R.id.sr_bar);
        setSupportActionBar(toolbar);

        no_artist = findViewById(R.id.empty_artist);
        artist_pro.setVisibility(View.INVISIBLE);
        no_artist.setVisibility(View.INVISIBLE);
        recyclerView = findViewById(R.id.artist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getSupportActionBar().setTitle(query);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            RequestQueue queue = Volley.newRequestQueue(this);
            //String url = "http://10.0.2.2:8080/search?q=" + query;
            String url = "https://csci571-131415.wl.r.appspot.com/search?q=" + query;
            artist_pro.setVisibility(View.VISIBLE);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            artist_pro.setVisibility(View.INVISIBLE);
                            JSONArray results;
                            try {
                                 results = response.getJSONObject("_embedded").getJSONArray("results");
                                 for (int i = 0; i < results.length(); i++) {
                                     String name = results.getJSONObject(i).getString("title");
                                     String id = results.getJSONObject(i).getJSONObject("_links").getJSONObject("self").getString("href");
                                     id = id.replace("https://api.artsy.net/api/artists/", "");
                                     String link = results.getJSONObject(i).getJSONObject("_links").getJSONObject("thumbnail").getString("href");
                                     artists.add(new ArtistCard(name, id, link));
                                 }
                                 if (artists.size() != 0) {
                                     ArtistRecyclerAdapter adapter = new ArtistRecyclerAdapter(artists, searchable.this::onArtistClick);
                                     recyclerView.setAdapter(adapter);
                                 } else {
                                     no_artist.setVisibility(View.VISIBLE);
                                 }


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
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onArtistClick(int position) {
        String id = artists.get(position).getId();
        String name = artists.get(position).getName();
        Intent intent = new Intent(this, DetailArtworks.class);
        Bundle extras = new Bundle();
        extras.putString("id", id);
        extras.putString("name", name);
        intent.putExtras(extras);
        startActivity(intent);
    }
}