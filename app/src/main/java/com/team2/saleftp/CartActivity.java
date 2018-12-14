package com.team2.saleftp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import adapter.CartAdapter;
import model.Cart;
import model.Product;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    TextView tvNoti;
    static TextView tvTotal;
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
        CheckData();
    }

    private void CheckData(){
        if(MainActivity.arrCart.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            tvNoti.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        }else {
            cartAdapter.notifyDataSetChanged();
            tvNoti.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }
    }

    private void AddCart(View view){

    }

    public static void Event(){
        long total = 0;
        for (int i = 0; i<MainActivity.arrCart.size(); i++){
            total += MainActivity.arrCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTotal.setText(decimalFormat.format(total)+ "Đ");
    }
}
