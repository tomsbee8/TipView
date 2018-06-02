package cn.blinkdagger.tipview.sample;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private Fragment tipViewFragment;
    private Fragment tipMenuViewFragment;

    String[] mTitles = new String[]{"TipView", "TipMenuView"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =findViewById(R.id.main_toolbar);
        mTabLayout =findViewById(R.id.main_tab_layout);
        mViewPager =findViewById(R.id.main_viewpager);
        setupToolBar();
        setupViewPager();

    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setupViewPager() {
        tipViewFragment = new TipViewFragment();
        tipMenuViewFragment = new TipMenuViewFragment();

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), new Fragment[]{tipViewFragment, tipMenuViewFragment}, mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
