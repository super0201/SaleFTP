package com.team2.saleftp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.OrderAdapter;
import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.User;


public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private EditText edtName, edtPhone, edtAddress;
    private ListView lvOrder;
    private TextView tvAmount, tvTotal;
    private Button btnOrder;

    private ArrayList<Product> listCart = new ArrayList<Product>();

    private OrderAdapter orderAdapter = null;

    private UserDAO userDAO;
    private ProductDAO productDAO;
    private static User updateUSER = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        analyze();

        userDAO = new UserDAO(OrderActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        edtName.setText(LoginActivity.USER.getName());
        edtAddress.setText(LoginActivity.USER.getAddr());
        edtPhone.setText(LoginActivity.USER.getPhone());

        productDAO = new ProductDAO(OrderActivity.this);
        listCart = productDAO.viewAll();

        orderAdapter = new OrderAdapter(this, listCart);
        lvOrder.setAdapter(orderAdapter);

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
        imvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setEnabled(true);
                edtPhone.setEnabled(true);
                edtAddress.setEnabled(true);
                imvInfo.setImageResource(android.R.drawable.ic_menu_save);
            }
        });

        edtName = (EditText) findViewById(R.id.tiedtNameOrder);
        edtName.setEnabled(false);

        edtPhone = (EditText) findViewById(R.id.tiedtPhoneOrder);
        edtPhone.setEnabled(false);

        edtAddress = (EditText) findViewById(R.id.tiedtAddressOrder);
        edtAddress.setEnabled(false);

        lvOrder = (ListView) findViewById(R.id.lvOrder);

        tvAmount = (TextView) findViewById(R.id.tvAmountOrder);
        tvTotal = (TextView) findViewById(R.id.tvTotalOrder);

        btnOrder = (Button) findViewById(R.id.btnOrder);

    }

}
