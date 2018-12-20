package com.appdorms.quickcount;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Presiden.OnFragmentInteractionListener, DPR.OnFragmentInteractionListener, PPD.OnFragmentInteractionListener, ViewFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;
    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_presiden:
                    viewPager.setCurrentItem(1);
//                    mTextMessage.setText(R.string.title_presiden);
                    return true;
                case R.id.navigation_ppd:
                    viewPager.setCurrentItem(2);
//                    mTextMessage.setText(R.string.title_ppd);
                    return true;
                case R.id.navigation_dpr:
                    viewPager.setCurrentItem(3);
//                    mTextMessage.setText(R.string.title_dpr);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setupViewPager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        //hanya untuk 5 fragments
        pagerAdapter.addFragments(new ViewFragment());
        pagerAdapter.addFragments(new Presiden());
        pagerAdapter.addFragments(new PPD());
        pagerAdapter.addFragments(new DPR());

        pagerAdapter.addFragments(createFragment(R.color.colorPrimaryDark));
        pagerAdapter.addFragments(createFragment(R.color.colorPrimaryDark));

        viewPager.setAdapter(pagerAdapter);
    }


    @NonNull
    private DummyFragment createFragment(int color) {
        DummyFragment fragment = new DummyFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private Bundle passFragmentArguments(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        return bundle;
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    @Override
//    public void onListFragmentInteraction(Presiden item) {
//
//    }


}
