package com.team2.saleftp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dao.ProductDAO;
import model.Cart;
import model.Product;
import model.ProductDetail;

/**
 * Created By JohnNguyen - Onesoft on 14/12/2018
 */
public class DetailActivity extends AppCompatActivity {
    private ArrayList<Product> data = new ArrayList<>();
    public static ProductDetail list;
    public static ArrayList<Cart>arrCart = new ArrayList<>();
    private ProductDAO dao;
    int pos;
    String id, nam, detaill, image;
    Integer pric, amount;
    TextView name, price, detail, scr, scrRes, frCam, reCam, cpu, ram ,rom, sim, mCard, battCap, os;
    Button btnBuy, btnAddCart;
    ImageView prod, imvBack, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dao = new ProductDAO(getBaseContext());

        //Textview
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        detail = findViewById(R.id.detail);
        scr = findViewById(R.id.scr);
        scrRes = findViewById(R.id.scrRes);
        frCam = findViewById(R.id.fronCam);
        reCam = findViewById(R.id.reCam);
        cpu = findViewById(R.id.cpu);
        ram = findViewById(R.id.ram);
        rom = findViewById(R.id.rom);
        sim = findViewById(R.id.sim);
        mCard = findViewById(R.id.memCard);
        battCap = findViewById(R.id.battCap);
        os = findViewById(R.id.os);

        //Button
        btnBuy = findViewById(R.id.btnBuy);
        btnAddCart = findViewById(R.id.btnAddCart);

        //Imageview
        prod = findViewById(R.id.prod);
        imvBack = findViewById(R.id.imvBack);
        cart = findViewById(R.id.cart);

        //get intent data
        data = getIntent().getParcelableArrayListExtra("data");
        pos = getIntent().getIntExtra("pos", 0);

        //get data from intent list on position
        id = data.get(pos).getId();

        //imvBtn set event
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add Dao insert to CartDB
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dao.insertCart(id);
//                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
                /*int amount=1;
                if (arrCart.size()>0){
                    for (int i=0;i<arrCart.size();i++){
                        if (arrCart.get(i).getIdproduct()==id){
                            arrCart.get(i).setAmount(arrCart.get(i).getAmount()+amount);
                            if (arrCart.get(i).getAmount()>=10){
                                arrCart.get(i).setAmount(10);
                            }
                            arrCart.get(i).setPrice((Double.parseDouble(pric)*arrCart.get(i).getAmount()));
                        }
                    }
                    double newprice=(Double.parseDouble(pric))*amount;
                    Log.e("addGioHang=",nam+"soluong="+amount+"Giamoi="+newprice);
                    arrCart.add(new Cart(id,nam,image,amount,newprice));
                }else {
                    double newprice=amount*(Double.parseDouble(pric));
                    Log.e("addGioHang222=",nam+"soluong="+amount+"Giamoi="+newprice);
                    arrCart.add(new Cart(id,nam,image,amount,newprice));

                }
                */

                //already have in setDetail() method, no need to call again
                dao.insertCart(id, nam, pric, amount, image);
                Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });

        //run method
        setDetail();

    }

    public void setDetail(){
        //get data from intent list on position
        id = data.get(pos).getId();
        image = data.get(pos).getImage();
        nam = data.get(pos).getName();
        pric = data.get(pos).getPrice();
        detaill = data.get(pos).getDetail();

        //set data to textview and imageview
        Glide.with(getBaseContext()).load(image).into(prod);
        name.setText(nam);
        int d = pric;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        price.setText(decimalFormat.format(d));
        detail.setText(detaill);

        scr.setText(MainActivity.list2.getScr());
        scrRes.setText(MainActivity.list2.getScrRes());
        frCam.setText(MainActivity.list2.getFrCam());
        reCam.setText(MainActivity.list2.getReCam());
        cpu.setText(MainActivity.list2.getCpu());
        ram.setText(MainActivity.list2.getRam());
        rom.setText(MainActivity.list2.getRom());
        sim.setText(MainActivity.list2.getSim());
        mCard.setText(MainActivity.list2.getmCard());
        battCap.setText(MainActivity.list2.getBattCap());
        os.setText(MainActivity.list2.getOs());
    }
}
