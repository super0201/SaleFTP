package com.team2.saleftp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.OrderAdapter;
import model.Cart;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private TextInputEditText tiedtName, tiedtPhone, tiedtAddress;
    private ListView lvOrder;
    private TextView tvAmount, tvTotal;
    private Button btnOrder;

    private ArrayList<Cart> listCart = new ArrayList<>();
    private OrderAdapter orderAdapter = null;
    private UserAdapter userAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        analyze();

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

        tiedtName = (TextInputEditText) findViewById(R.id.tiedtNameOrder);
        tiedtPhone = (TextInputEditText) findViewById(R.id.tiedtPhoneOrder);
        tiedtAddress = (TextInputEditText) findViewById(R.id.tiedtAddressOrder);

        lvOrder = (ListView) findViewById(R.id.lvOrder);

        tvAmount = (TextView) findViewById(R.id.tvAmountOrder);
        tvTotal = (TextView) findViewById(R.id.tvTotalOrder);

        btnOrder = (Button) findViewById(R.id.btnOrder);

    }

}
