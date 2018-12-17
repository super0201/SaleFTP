package com.team2.saleftp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import adapter.CartAdapter;
import dao.CartDAO;
import database.CartDB;
import model.Cart;
import model.Product;

public class CartActivity extends AppCompatActivity {
    ListView lvCart;
    TextView tvNoti;
    static TextView tvTotal;
    Button btnPayment, btnContinue;
    CartAdapter cartAdapter;
    CartDAO cartDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Giỏ hàng");
        lvCart = findViewById(R.id.lvCart);
        tvNoti = findViewById(R.id.tvNoti);
        tvTotal = findViewById(R.id.tvTotal);

        CheckData();
        CatchOnItemListView();
        AddCart();

        btnPayment = findViewById(R.id.btnPay);
        btnContinue = findViewById(R.id.btnContinue);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void CatchOnItemListView(){
        lvCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Bạn có muốn xóa sản phẩm này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(MainActivity.arrCart.size()<=0){
//                            tvNoti.setVisibility(View.INVISIBLE);
//                        }else {
//                            MainActivity.arrCart.remove(i);
//                            cartAdapter.notifyDataSetChanged();
//                            Event();
//                            if(MainActivity.arrCart.size() <= 0){
//                                tvNoti.setVisibility(View.VISIBLE);
//                            }else {
//                                tvNoti.setVisibility(View.INVISIBLE);
//                            }
//                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        Event();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void CheckData(){
//        if(MainActivity.arrCart.size() <= 0){
//            cartAdapter.notifyDataSetChanged();
//            tvNoti.setVisibility(View.VISIBLE);
//            lvCart.setVisibility(View.INVISIBLE);
//        }else {
//            cartAdapter.notifyDataSetChanged();
//            tvNoti.setVisibility(View.INVISIBLE);
//            lvCart.setVisibility(View.VISIBLE);
//        }
    }

    private void AddCart(){
        int x = cartDAO.viewCart().size();
        for (int i = 0; i < x; i++) {
            lvCart.setAdapter((ListAdapter) cartDAO.viewCart());
        }

    }

    public static void Event(){
//        long total = 0;
//        for (int i = 0; i<MainActivity.arrCart.size(); i++){
//            total += MainActivity.arrCart.get(i).getPrice();
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        tvTotal.setText(decimalFormat.format(total)+ "Đ");
    }
}
