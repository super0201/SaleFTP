package com.team2.saleftp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Objects;

import adapter.ProductMainAdapter;
import adapter.RecyclerItemClickListener;
import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.ProductDetail;
import model.User;
import session.SessionManager;

/**
 * Created By JohnNguyen - Onesoft on 12/12/2018
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> list = new ArrayList<>();
    public static ProductDetail list2;
    ProductDAO dao;
    SearchView search;
    ImageView imvProfile;
    FloatingActionButton fab;
    ProductMainAdapter mAdapter;
    RecyclerView mRecyclerView;
    SessionManager sessionManager;
    UserDAO userDAO;
    public static User USER = null;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getBaseContext());
        dao = new ProductDAO(getBaseContext());
        list = dao.viewAll();

        fab = findViewById(R.id.fab);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CartActivity.class);
                startActivity(i);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getBaseContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //intent Parcelable data to DetailActivity
                        String id;
                        id = list.get(position).getId();
                        list2 = dao.viewDetail(id);
                        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                        intent.putParcelableArrayListExtra("data", list);
                        intent.putExtra("pos", position);
                        startActivityForResult(intent, 10001);
                    }
                }));
    }

    public void closeKeyboard() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) this.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            Objects.requireNonNull(imm).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.raw.logo)
                .setTitle("FTP")
                .setMessage("Bạn Muốn Thoát Ứng Dụng?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void initial() {
        SessionManager session = new SessionManager(getBaseContext());
        userDAO = new UserDAO(getBaseContext());
        if (session.isLoggedIn()) {
//        String x = session.getSharedUsername();
            LoginActivity.USER = userDAO.getUserByUsername(session.getSharedUsername());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initial();
        closeKeyboard();
    }
}
