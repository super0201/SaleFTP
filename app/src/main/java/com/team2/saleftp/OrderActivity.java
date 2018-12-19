package com.team2.saleftp;

import android.app.Dialog;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;

import adapter.GetDateX;
import adapter.OrderAdapter;
import dao.InvoiceDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Cart;
import model.User;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private EditText edtName, edtPhone, edtAddress;
    private ListView lvOrder;
    private TextView tvAmountOrder, tvTotalOrder;
    private Button btnOrder;

    String name, phone, addre;

    InvoiceDAO invoiceDAO;

    private ArrayList<Cart> listCart = new ArrayList<>();

    private OrderAdapter orderAdapter = null;

    private UserDAO userDAO;
    private ProductDAO productDAO;

    int count = 0, a = 0;
    double b = 0, c = 0;

    int Code;
    int min = 111111;
    int max = 222222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        final Date date = new Date();

        Code = min+(int)(Math.random()*(max-min+1));

        initialize();

        productDAO = new ProductDAO(getBaseContext());

        userDAO = new UserDAO(OrderActivity.this);

        listCart = productDAO.viewAllCart();

        if (savedInstanceState != null){

            name = (String)savedInstanceState.getString("NAME");
            phone = (String)savedInstanceState.getString("PHONE");
            addre = (String)savedInstanceState.getString("ADDRESS");

            edtName.setText(name);
            edtPhone.setText(phone);
            edtAddress.setText(addre);

        } else {

                edtName.setText(LoginActivity.USER.getName());
                edtAddress.setText(LoginActivity.USER.getAddr());
                edtPhone.setText(LoginActivity.USER.getPhone());

        }



        orderAdapter = new OrderAdapter(this, listCart);
        lvOrder.setAdapter(orderAdapter);


//        for (Cart x : listCart) {
//            a = x.getAmount();
//            b = x.getPrice();
//            c += (a*b);
//            return;
//        }

        //tvAmountOrder.setText(listCart.size());
        tvTotalOrder.setText(CartActivity.tvTotal.getText());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invoiceDAO.insertInvoice(getNameCart(), String.valueOf(Code), GetDateX.getDateString(String.valueOf(date)), getStt());
                dialogOrder();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        name = edtName.getText().toString();
        addre = edtAddress.getText().toString();
        phone = edtPhone.getText().toString();

        outState.putString("NAME", name);
        outState.putString("ADDRESS", addre);
        outState.putString("PHONE", phone);

        super.onSaveInstanceState(outState);

    }

    private void initialize() {

        imvBack = (ImageView) findViewById(R.id.imvBack);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
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

    private String getNameCart() {
        String nam = null;
        for (Cart x : listCart) {
            nam = x.getName();
        }
        return nam;
    }

    private String getStt() {
        String stt;
        if (btnOrder.isClickable()) {
            stt = "Đang giao hàng";
        } else {
            stt = null;
        }
        return stt;
    }

    private void dialogOrder(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
        TextView tvCode = (TextView) dialog.findViewById(R.id.tvCode);
        Button btnBack = (Button)dialog.findViewById(R.id.btnBack);

        tvCode.setText("");

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
