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
import session.SessionManager;

/**
 * Created By JohnNguyen - Onesoft on 12/12/2018
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> list = new ArrayList<>();
    ProductDAO dao;
    SearchView search;
    ImageView imvProfile;
    ProductMainAdapter mAdapter;
    RecyclerView mRecyclerView;
    SessionManager sessionManager;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getBaseContext());
        dao = new ProductDAO(getBaseContext());
        list = dao.viewAll();

        search = findViewById(R.id.search);
        imvProfile = findViewById(R.id.imvProfile);

        //custom searchview
        search.isIconfiedByDefault();
        closeKeyboard();

        //setOnClick profile
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert your profile code here
                if (sessionManager.isLoggedIn()){
                    Intent i = new Intent(getBaseContext(), UserInfoActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });

        //list product adapter
        mRecyclerView = findViewById(R.id.rvMain);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ProductMainAdapter(getBaseContext(), list);
        mRecyclerView.setAdapter(mAdapter);

        //setOnClick event
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //intent Parcelable data to DetailActivity
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
