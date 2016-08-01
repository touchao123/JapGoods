package com.touchenjoy.japgoods.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.touchenjoy.japgoods.R;
import com.touchenjoy.japgoods.ui.fragment.AllGoodsFragment;
import com.touchenjoy.japgoods.ui.fragment.CarsFragment;
import com.touchenjoy.japgoods.ui.fragment.ClothesFragment;
import com.touchenjoy.japgoods.ui.fragment.FoodsFragment;
import com.touchenjoy.japgoods.ui.fragment.ResideFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    final  String TAG="MainActivity";
    private BottomBar mBottomBar;

    @Bind(R.id.main_coordinator_activity)
    CoordinatorLayout coordinatorLayout;


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


        //bottom navigator bar
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                switch (menuItemId){
                    case R.id.bottomBarItemHome:
                        Snackbar.make(coordinatorLayout, "bottomBarItemHome", 300).show();
                        break;
                    case R.id.bottomBarItemForum:
                        Snackbar.make(coordinatorLayout, "bottomBarItemForum", 300).show();
                        break;
                    case R.id.bottomBarItemShop:
                        Snackbar.make(coordinatorLayout, "bottomBarItemShop", 300).show();
                        break;
                    case R.id.bottomBarItemMe:
                        Snackbar.make(coordinatorLayout, "bottomBarItemMe", 300).show();
                        break;
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                switch (menuItemId){
                    case R.id.bottomBarItemHome:
                        break;
                }
            }
        });

    }

    private void initViews(){

        //Header status bar
        setSupportActionBar(toolBar);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                    return new ClothesFragment();
//                        return new CarsFragment();
                    case 1:
                        return new FoodsFragment();
//                        return new CarsFragment();
                    case 2:
                        return new ResideFragment();
//                        return new CarsFragment();
                    case 3:
                        return new CarsFragment();
                    case 4:
                        return new AllGoodsFragment();
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
                    case 4:
                        return getString(R.string.allgoods);
                    default:
                        return getString(R.string.cars);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        toolBar.setTitle(R.string.app_name);
        toolBar.setSubtitle(R.string.app_sub_title);
//        toolBar.setLogo(R.drawable.btn_common);

//        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.ic_crop_free_white_36dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG,"navigation pressed");
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
//                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt(getString(R.string.qr_prompt));
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(true);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();

            }
        });
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
                default:
                    break;

            }
            return true;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.clearFocus();

        // Associate searchable configuration with the SearchView
//        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//               Log.d( TAG,"SearchOnQueryTextSubmit: " + query);
//                if( ! searchView.isIconified()) {
//                    searchView.setIconified(true);
//                }
//                myActionMenuItem.collapseActionView();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
        /**/
        //MessageListMainFragment.this.adapter.getFilter().filter(newText);
//                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
//                return false;
//            }
//        });
//
        return true;

    }
}
