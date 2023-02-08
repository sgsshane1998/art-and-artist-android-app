package com.example.artistsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView today;
    private ConstraintLayout pro_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pro_bar = findViewById(R.id.pro_bar);
        pro_bar.setVisibility(View.VISIBLE);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pro_bar.setVisibility(View.INVISIBLE);
            }
        }, 3000);

        TextView textView =(TextView)findViewById(R.id.artsy_link);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.artsy.net/'>Powered by Artsy</a>";
        textView.setText(Html.fromHtml(text));
        textView.setLinkTextColor(getResources().getColor(R.color.artsy_link_color));

        toolbar = findViewById(R.id.main_toolbar);
        today = findViewById(R.id.dateYear);
        String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String now = simpleDateFormat.format(new Date());
        today.setText(now);

        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchview, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Search...");
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(
                        new ComponentName(getApplicationContext(), searchable.class)));

        return super.onCreateOptionsMenu(menu);
    }


}