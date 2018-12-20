package com.team2.saleftp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.GetDateX;
import adapter.OrderAdapter;
import dao.InvoiceDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Cart;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private EditText edtName, edtPhone, edtAddress;
    private ListView lvOrder;
    private TextView tvAmountOrder, tvTotalOrder;
    private Button btnOrder;

    String name, phone, addre;

    InvoiceDAO invoiceDAO;

    private ArrayList<Cart> listCart = new ArrayList<>();

    private OrderAdapter orderAdapter;

    private ProductDAO productDAO;

    int count = 0, a = 0;

    int Code;
    int min = 111111;
    int max = 222222;

    GetDateX getDateX;


    private static int REQUEST_CODE_EXAMPLE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("TIẾN HÀNH ĐẶT HÀNG");

        final Date date = new Date();

        Code = min+(int)(Math.random()*(max-min+1));

        productDAO = new ProductDAO(getBaseContext());
        listCart = productDAO.viewAllCart();

        orderAdapter = new OrderAdapter(this, listCart);

        initialize();

        lvOrder.setAdapter(orderAdapter);

        for (Cart x : listCart) {
            a += x.getAmount();
        }

        tvAmountOrder.setText("Tổng sản phẩm: " + String.valueOf(a));

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invoiceDAO.insertInvoice(getNameCart(), String.valueOf(Code), getDateX.getDateString(String.valueOf(date)), getStt());
                dialogOrder();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        name = edtName.getText().toString();
        addre = edtAddress.getText().toString();
        phone = edtPhone.getText().toString();

        outState.putString("NAME", name + 1);
        outState.putString("ADDRESS", addre + 1);
        outState.putString("PHONE", phone + 1);

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

        Event();

        btnOrder = (Button) findViewById(R.id.btnOrder);

            edtName.setText(LoginActivity.USER.getName());
            edtAddress.setText(LoginActivity.USER.getAddr());
            edtPhone.setText(LoginActivity.USER.getPhone());

        }
//    }

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

    private void Event() {
        double total = 0;
        for (int i = 0; i < listCart.size(); i++){
            int x = listCart.get(i).getAmount();
            int y = listCart.get(i).getPrice();
            total += x * y;
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTotalOrder.setText("Tổng tiền: " + decimalFormat.format(total)+ "Đ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        listCart.clear();
        listCart = productDAO.viewAllCart();
        orderAdapter.changeDataset(listCart);
    }
}
