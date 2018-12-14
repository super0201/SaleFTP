package com.team2.saleftp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import adapter.CartAdapter;
import model.Cart;
import model.Product;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    TextView tvNoti, tvTotal;
    Button btnPayment, btnContinue;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Giỏ hàng");
        lvCart = findViewById(R.id.lvCart);
        tvNoti = findViewById(R.id.tvNoti);
        tvTotal = findViewById(R.id.tvTotal);
        btnPayment = findViewById(R.id.btnPay);
        btnContinue = findViewById(R.id.btnContinue);
        cartAdapter = new CartAdapter(CartActivity.this, MainActivity.arrCart);
        lvCart.setAdapter(cartAdapter);
    }
}
