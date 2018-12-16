package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;

import adapter.ProductMainAdapter;
import adapter.RecyclerItemClickListener;
import dao.ProductDAO;
import model.Product;
import model.ProductDetail;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> list = new ArrayList<>();
    private ArrayList<ProductDetail> list2 = new ArrayList<>();
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
        mRecyclerView = findViewById(R.id.rvMain);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ProductMainAdapter(getBaseContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        //run search data in Detail table with id in Product table
//                        String id = list.get(position).getId();
//                        list2 = dao.viewDetail(id);

                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", list);
//                        intent.putParcelableArrayListExtra("data2", list2);
                        intent.putExtra("pos", position);
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void closeKeyboard() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) this.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
