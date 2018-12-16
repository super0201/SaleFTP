package com.team2.saleftp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;
import model.ProductDetail;

public class DetailActivity extends AppCompatActivity {
    private ArrayList<Product> data = new ArrayList<>();
    private ArrayList<ProductDetail> list = new ArrayList<>();
    private ProductDAO dao;
    int pos;
    String id, nam, pric, detail;
    String scrDt, scrResDt, frCamDt, reCamDt, cpuDt, ramDt, romDt, simDt, mCardDt, battCapDt, osDt;
    TextView name, price, scr, scrRes, frCam, reCam, cpu, ram ,rom, sim, mCard, battCap, os;
    Button btnBuy, btnAddCart;
    ImageView prod;

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

        //get intent data
        data = getIntent().getParcelableArrayListExtra("data");
        pos = getIntent().getIntExtra("pos", 0);

        id = data.get(pos).getId();
        nam = data.get(pos).getName();
        pric = data.get(pos).getPrice();
        detail = data.get(pos).getDetail();

        //run search data in ProductDetail with id in ProductDB
        list = dao.viewDetail(id);
    }

    public void setDetail(){
        scrDt = list.get
        scrResDt
        frCamDt
        reCamDt
        cpuDt
        ramDt
        romDt
        simDt
        mCardDt
        battCapDt
        osDt
        name.setText();
    }
}
