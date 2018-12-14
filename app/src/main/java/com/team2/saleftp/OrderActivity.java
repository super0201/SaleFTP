package com.team2.saleftp;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.OrderAdapter;
import dao.UserDAO;
import model.Cart;
import model.User;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private TextInputEditText tiedtName, tiedtPhone, tiedtAddress;
    private TextInputLayout tilNameOrder;
    private ListView lvOrder;
    private TextView tvAmount, tvTotal;
    private Button btnOrder;

    private ArrayList<Cart> listCart = new ArrayList<>();
    private ArrayList<User> listUser = new ArrayList<>();

    private OrderAdapter orderAdapter = null;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        analyze();

<<<<<<< HEAD
        userDAO = new UserDAO(OrderActivity.this);
/*
        edtName.setText(LoginActivity.USER.getName());
        edtAddress.setText(LoginActivity.USER.getAddr());
        edtPhone.setText(LoginActivity.USER.getPhone());*/

        productDAO = new ProductDAO(OrderActivity.this);
        listCart = productDAO.viewAll();

        orderAdapter = new OrderAdapter(this, listCart);
        lvOrder.setAdapter(orderAdapter);

=======
>>>>>>> parent of 515945e... Z
    }

    private void analyze() {
        imvBack = (ImageView) findViewById(R.id.imvBack);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imvInfo = (ImageView) findViewById(R.id.imvInfoOrder);
<<<<<<< HEAD
        imvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        edtName.setEnabled(true);
                        edtPhone.setEnabled(true);
                        edtAddress.setEnabled(true);
                        imvInfo.setImageResource(android.R.drawable.ic_menu_save);
                    }
                }).start();
            }
        });

        edtName = (EditText) findViewById(R.id.edtNameOder);
        edtName.setEnabled(false);

        edtPhone = (EditText) findViewById(R.id.edtPhoneOrder);
        edtPhone.setEnabled(false);

        edtAddress = (EditText) findViewById(R.id.edtAddressOrder);
        edtAddress.setEnabled(false);
=======

        tilNameOrder = (TextInputLayout) findViewById(R.id.tilNameOrder);
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtNameOrder);
        tiedtName.setEnabled(false);

        tiedtPhone = (TextInputEditText) findViewById(R.id.tiedtPhoneOrder);
        tiedtPhone.setEnabled(false);

        tiedtAddress = (TextInputEditText) findViewById(R.id.tiedtAddressOrder);
        tiedtAddress.setEnabled(false);
>>>>>>> parent of 515945e... Z

        lvOrder = (ListView) findViewById(R.id.lvOrder);

        tvAmount = (TextView) findViewById(R.id.tvAmountOrder);
        tvTotal = (TextView) findViewById(R.id.tvTotalOrder);

        btnOrder = (Button) findViewById(R.id.btnOrder);

    }

}
