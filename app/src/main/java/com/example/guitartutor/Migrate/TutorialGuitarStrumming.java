package com.example.guitartutor.Migrate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.Adapters.TabAdapter;
import com.example.guitartutor.Migrate.Adapters.TabStrumming1;
import com.example.guitartutor.Migrate.Adapters.TabStrumming2;

public class TutorialGuitarStrumming extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_guitar_strumming);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabStrumming1(), "Technique and Timing Tips");
        adapter.addFragment(new TabStrumming2(), "Seven Strum Patterns");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
