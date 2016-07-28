package com.touchenjoy.japgoods.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.touchenjoy.japgoods.R;
import com.touchenjoy.japgoods.ui.fragment.CarsFragment;
import com.touchenjoy.japgoods.ui.fragment.ClothesFragment;
import com.touchenjoy.japgoods.ui.fragment.FoodsFragment;
import com.touchenjoy.japgoods.ui.fragment.ResideFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    final  String TAG="MainActivity";
    @Bind(android.R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews(){

        setSupportActionBar(toolBar);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
//                    return new ClothesFragment();
                        return new CarsFragment();
                    case 1:
//                        return new FoodsFragment();
                        return new CarsFragment();
                    case 2:
//                        return new ResideFragment();
                        return new CarsFragment();
                    case 3:
                    default:
                        return new CarsFragment();
                }

            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.clothes);
                    case 1:
                        return getString(R.string.foods);
                    case 2:
                        return getString(R.string.reside);
                    case 3:
                        return getString(R.string.cars);
                    default:
                        return getString(R.string.cars);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        toolBar.setTitle("JapGoods");
        toolBar.setSubtitle("search Goods");
//        toolBar.setLogo(R.drawable.btn_common);

        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.icon_new);
        toolBar.setOnMenuItemClickListener(onMenuItemClick);

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit:
                    Log.i(TAG,"edit");
                    break;
                case R.id.action_settings:
                    Log.i(TAG,"settings");
                    break;
                case R.id.action_share:
                    Log.i(TAG,"action_share");
                    break;
                default:
                    break;

            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        // Associate searchable configuration with the SearchView
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               Log.d( TAG,"SearchOnQueryTextSubmit: " + query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });

        return true;

    }
}
