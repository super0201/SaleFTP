package com.team2.saleftp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

import adapter.OrderAdapter;
import dao.UserDAO;
import model.Cart;
import model.User;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private EditText edtName, edtPhone, edtAddress;
    private ListView lvOrder;
    private TextView tvAmountOrder, tvTotalOrder;
    private Button btnOrder;

    private ArrayList<Cart> listCart = new ArrayList<>();
    private ArrayList<User> listUser = new ArrayList<>();

    private OrderAdapter orderAdapter = null;

    private UserDAO userDAO;

    int count = 0, a = 0;
    double b = 0, c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        analyze();


        userDAO = new UserDAO(OrderActivity.this);

        edtName.setText(LoginActivity.USER.getName());
        edtAddress.setText(LoginActivity.USER.getAddr());
        edtPhone.setText(LoginActivity.USER.getPhone());


        orderAdapter = new OrderAdapter(this, listCart);
        lvOrder.setAdapter(orderAdapter);

        for (Cart x : listCart) {
            a = x.getAmount();
            b = x.getPrice();
            c += (a*b);
            return;
        }

        tvAmountOrder.setText(listCart.size());
        tvTotalOrder.setText(CartActivity.tvTotal.getText());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOrder();
            }
        });
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

                    if (count == 0) {
                        edtName.setEnabled(true);
                        edtPhone.setEnabled(true);
                        edtAddress.setEnabled(true);
                        imvInfo.setImageResource(android.R.drawable.ic_menu_save);
                        count++;
                    } else {
                        edtName.setEnabled(false);
                        edtPhone.setEnabled(false);
                        edtAddress.setEnabled(false);
                        imvInfo.setImageResource(android.R.drawable.ic_menu_edit);
                        --count;
                    }
            }
        });


        edtName = (EditText) findViewById(R.id.edtNameOrder);
        edtName.setEnabled(false);

        edtPhone = (EditText) findViewById(R.id.edtPhoneOrder);
        edtPhone.setEnabled(false);

        edtAddress = (EditText) findViewById(R.id.edtAddressOrder);
        edtAddress.setEnabled(false);

        lvOrder = (ListView) findViewById(R.id.lvOrder);

        tvAmountOrder = (TextView) findViewById(R.id.tvAmountOrder);
        tvTotalOrder = (TextView) findViewById(R.id.tvTotalOrder);

        btnOrder = (Button) findViewById(R.id.btnOrder);

    }

    private void dialogOrder(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
        TextView tvCode = (TextView) dialog.findViewById(R.id.tvCode);
        Button btnBack = (Button)dialog.findViewById(R.id.btnBack);
        int Code;
        int min = 11111;
        int max = 22222;
        tvCode.setText("");
        Code = min+(int)(Math.random()*(max-min+1));
        tvCode.append("FTP" + Code);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
