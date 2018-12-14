package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.team2.saleftp.fragment.SliderFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import adapter.ProductMainAdapter;
import adapter.SliderAdapter;
import dao.ProductDAO;
import model.Product;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private RadioGroup group;
    private ArrayList<Product> list = new ArrayList<>();
    int currentPage = 0;
    final long DELAY_MS = 2000;
    final long PERIOD_MS = 6000;
    ProductDAO dao;
    Timer timer;
    SearchView search;
    ImageView imvProfile;
    ProductMainAdapter mAdapter;
    RecyclerView mRecyclerView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new ProductDAO(getBaseContext());
        list = dao.viewAll();

        search = (SearchView) findViewById(R.id.search);
        viewPager = (ViewPager) findViewById(R.id.image_slider);
        group = (RadioGroup) findViewById(R.id.slider_indicator_group);
        group.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);

        //custom searchview
        search.isIconfiedByDefault();
        closeKeyboard();

        //slide adapter
        SliderAdapter adapter = new SliderAdapter(getSupportFragmentManager());
        adapter.addFragment(SliderFragment.newInstance("https://i.imgur.com/gru5CzF.jpg"));
        adapter.addFragment(SliderFragment.newInstance("https://i.imgur.com/gje3Qrp.jpg"));
        adapter.addFragment(SliderFragment.newInstance("https://i.imgur.com/KtfZl9w.jpg"));
        adapter.addFragment(SliderFragment.newInstance("https://i.imgur.com/0vR2t75.jpg"));
        adapter.addFragment(SliderFragment.newInstance("https://i.imgur.com/WItCuxV.jpg"));
        viewPager.setAdapter(adapter);

        //list product adapter
        mRecyclerView = (RecyclerView) findViewById(R.id.rvMain);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ProductMainAdapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
//                Intent i = new Intent(getBaseContext(), BasketActivity.class);
//                i.putExtra("data", list);
//                startActivity(i);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


        //slide auto image
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (1 == 5 - 1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new

                Timer(); // This will create a new Thread
        timer.schedule(new

                               TimerTask() { // task to be scheduled
                                   @Override
                                   public void run () {
                                       handler.post(Update);
                                   }
                               },DELAY_MS,PERIOD_MS);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void closeKeyboard() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) this.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        // when current page change -> update radio button state
        int radioButtonId = group.getChildAt(position).getId();
        group.check(radioButtonId);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        // when checked radio button -> update current page
        viewPager.setCurrentItem(group.indexOfChild(group.findViewById(checkedId)), true);
    }

}
