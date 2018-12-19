package com.team2.saleftp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import adapter.CartAdapter;
import dao.ProductDAO;
import model.Cart;
import session.SessionManager;

public class CartActivity extends AppCompatActivity {
    private ArrayList<Cart> cart = new ArrayList<>();
    RecyclerView lvCart;
    TextView tvNoti;
    static TextView tvTotal;
    Button btnPayment, btnContinue;
    SessionManager sessionManager;
    CartAdapter cartAdapter;
    ProductDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCart = findViewById(R.id.lvCart);
//        tvNoti = findViewById(R.id.tvNoti);
//        tvTotal = findViewById(R.id.tvTotal);

        sessionManager = new SessionManager(getBaseContext());
        dao = new ProductDAO(getBaseContext());
        cart = dao.viewAllCart();

        lvCart = findViewById(R.id.lvCart);
        lvCart.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));

        lvCart.setHasFixedSize(true);

        cartAdapter = new CartAdapter(getBaseContext(), cart);

        lvCart.setAdapter(cartAdapter);
        cartAdapter.changeDataset(cart);

        if (cart.size() > 0){
            cartAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getBaseContext(), "Không Có Hàng Trong Giỏ", Toast.LENGTH_SHORT).show();
        }

        btnPayment = findViewById(R.id.btnPay);
        btnContinue = findViewById(R.id.btnContinue);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLoggedIn()){
                    Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                    startActivity(intent);
                } else {
                    Intent i =  new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                }

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

//    private void CatchOnItemListView(){
//        lvCart.(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
//                builder.setTitle("Xác nhận");
//                builder.setMessage("Bạn có muốn xóa sản phẩm này ?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if(DetailActivity.arrCart.size()<=0){
//                            tvNoti.setVisibility(View.INVISIBLE);
//                        }else {
//                            DetailActivity.arrCart.remove(i);
//                            cartAdapter.notifyDataSetChanged();
//                            Event();
//                            if(DetailActivity.arrCart.size() <= 0){
//                                tvNoti.setVisibility(View.VISIBLE);
//                            }else {
//                                tvNoti.setVisibility(View.INVISIBLE);
//                            }
//                        }
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        cartAdapter.notifyDataSetChanged();
//                        Event();
//                    }
//                });
//                builder.show();
//                return true;
//            }
//        });
//    }

//    public void Event(){
//        double total = 0;
//        for (int i = 0; i < cart.size(); i++){
//            int y = cart.get(i).getPrice();
//            total +=  y;
//        }
//
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        tvTotal.setText(decimalFormat.format(total)+ "Đ");
//    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (cart.size() > 0){
            cartAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

//    private void CheckData(){
//        if(cart.size() <= 0){
//            cartAdapter.notifyDataSetChanged();
////            tvNoti.setVisibility(View.VISIBLE);
//            lvCart.setVisibility(View.INVISIBLE);
//        }else {
//            cartAdapter.notifyDataSetChanged();
////            tvNoti.setVisibility(View.INVISIBLE);
//            lvCart.setVisibility(View.VISIBLE);
//        }
//    }

}
