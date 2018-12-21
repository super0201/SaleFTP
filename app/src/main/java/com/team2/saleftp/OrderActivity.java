package com.team2.saleftp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import adapter.OrderAdapter;
import dao.InvoiceDAO;
import dao.ProductDAO;
import model.Cart;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo, imvBack;
    private EditText edtName, edtPhone, edtAddress;
    private ListView lvOrder;
    private TextView tvAmountOrder, tvTotalOrder;
    private Button btnOrder;

    String name, phone, addre;

    private ArrayList<Cart> listCart = new ArrayList<>();

    private OrderAdapter orderAdapter;

    private ProductDAO productDAO;
    private InvoiceDAO invoiceDAO;

    int count = 0, a = 0;

    int min = 111111;
    int max = 222222;

    final String Code = "FTP" + String.valueOf(min+(int)(Math.random()*(max-min+1)));

    private static int REQUEST_CODE_EXAMPLE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
//        Date date = inputFormat.parse(inputText);
//        String outputText = outputFormat.format(date);

        productDAO = new ProductDAO(getBaseContext());
        invoiceDAO = new InvoiceDAO(getBaseContext());

        listCart = productDAO.viewAllCart();

        orderAdapter = new OrderAdapter(this, listCart);

        initialize();

        lvOrder.setAdapter(orderAdapter);
        Utility.setListViewHeightBasedOnChildren(lvOrder);

        for (Cart x : listCart) {
            a += x.getAmount();
        }

        tvAmountOrder.setText("Tổng sản phẩm: " + a);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codez = Code;
                String namez = getName();
                int amountz = a;
                double totalz = Event();
                String datz = getDate();
                String sttz = getStt();

                if (listCart.size() == 0){
                    Toast.makeText(getBaseContext(), "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                } else {
                    invoiceDAO.insertInvoice(codez, namez, amountz, totalz, datz, sttz);
                    dialogOrder();
                }
            }
        });

        closeKeyboard();
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

//    private String getCode() {
//        String Code = String.valueOf(min+(int)(Math.random()*(max-min+1)));
//        return Code;
//    }

    private String getName() {
        String nam = "";
        try {
            for (Cart x : listCart) {
                nam += x.getName() + ";";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nam;
    }

    private String getDate() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy");

        String getDat = String.valueOf(new Date());

        Date datInput = null;

        String outputDat;


        try {
            datInput = inputDateFormat.parse(getDat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        outputDat = outputDateFormat.format(datInput);


        return outputDat;
    }

    private String getStt() {
        String stt = null;
        try {
            if (btnOrder.isClickable()) {
                stt = "Đang giao hàng";
            } else {
                stt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stt;
    }

    public void closeKeyboard() {
        View currentFocus = this.getCurrentFocus();
        if (currentFocus != null) {
            android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) this.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            Objects.requireNonNull(imm).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initialize() {

        imvBack = (ImageView) findViewById(R.id.imvBack);

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
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

    private void dialogOrder(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
        TextView tvCode = (TextView) dialog.findViewById(R.id.tvCode);
        Button btnBack = (Button)dialog.findViewById(R.id.btnBack);

        tvCode.setText("");
        String cod = Code;
        tvCode.append(cod);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private double Event() {
        double total = 0;
        for (int i = 0; i < listCart.size(); i++){
            int x = listCart.get(i).getAmount();
            int y = listCart.get(i).getPrice();
            total += x * y;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTotalOrder.setText("Tổng tiền: " + decimalFormat.format(total)+ "Đ");
        return total;
    }

    //Show all item in Listview;
    public static class Utility
    {
        public static void setListViewHeightBasedOnChildren(ListView listView)
        {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null)
            {
                return;
            }
            int totalHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
            for (int i = 0; i < listAdapter.getCount(); i++)
            {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listCart.clear();
        listCart = productDAO.viewAllCart();
        orderAdapter.changeDataset(listCart);
    }
}
