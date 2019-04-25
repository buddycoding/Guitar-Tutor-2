package com.example.guitartutor.Migrate;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guitartutor.R;
import com.example.guitartutor.Migrate.Adapters.Tab1;
import com.example.guitartutor.Migrate.Adapters.Tab2;
import com.example.guitartutor.Migrate.Adapters.Tab3;
import com.example.guitartutor.Migrate.Adapters.Tab4;
import com.example.guitartutor.Migrate.Adapters.TabAdapter;

public class TutorialGuitarHolding extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_guitar_holding);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(), "Resting Guitar (Right Leg)");
        adapter.addFragment(new Tab2(), "Resting Guitar (Left Leg)");
        adapter.addFragment(new Tab3(), "Correct Thumb Placement");
        adapter.addFragment(new Tab4(), "Rest Fingers For Support");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
