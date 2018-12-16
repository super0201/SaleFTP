package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import adapter.ProductMainAdapter;
import adapter.RecyclerItemClickListener;
import dao.ProductDAO;
import model.Cart;
import model.Product;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> list = new ArrayList<>();
    public static ArrayList<Cart> arrCart;
    ProductDAO dao;
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

        search = findViewById(R.id.search);

        //custom searchview
        search.isIconfiedByDefault();
        closeKeyboard();

        //list product adapter
        mRecyclerView = (RecyclerView) findViewById(R.id.rvMain);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ProductMainAdapter(getBaseContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", list);
                        intent.putExtra("pos", position);
                        startActivityForResult(intent, 10001);
                    }
                }));
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


}
