package com.team2.saleftp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;
import model.ProductDetail;

public class DetailActivity extends AppCompatActivity {
    private ArrayList<Product> data = new ArrayList<>();
    private ArrayList<ProductDetail> data2 = new ArrayList<>();
    private ProductDAO dao;
    int pos;
    String id, nam, pric, detaill, image;
    String scrDt, scrResDt, frCamDt, reCamDt, cpuDt, ramDt,romDt, simDt, mCardDt, battCapDt, osDt;
    TextView name, price, detail, scr, scrRes, frCam, reCam, cpu, ram ,rom, sim, mCard, battCap, os;
    Button btnBuy, btnAddCart;
    ImageView prod, imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dao = new ProductDAO(getBaseContext());

        //Textview
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);

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

        //back button
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //        //run search data in ProductDetail with id in ProductDB
//        list = dao.viewDetail(id);

        //run method
        setDetail();

    }

    public void setDetail(){
        //get intent data
        data = getIntent().getParcelableArrayListExtra("data");
        data2 = getIntent().getParcelableArrayListExtra("data2");
        pos = getIntent().getIntExtra("pos", 0);

        id = data.get(pos).getId();
        image = data.get(pos).getImage();
        nam = data.get(pos).getName();
        pric = data.get(pos).getPrice();
        detaill = data.get(pos).getDetail();


        Glide.with(getBaseContext()).load(image).into(prod);
        name.setText(nam);
        price.setText(pric);
//        detail.setText(detaill);
    }
}
