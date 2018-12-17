package com.team2.saleftp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dao.CartDAO;
import dao.ProductDAO;
import model.Product;
import model.ProductDetail;

/**
 * Created By JohnNguyen - Onesoft on 14/12/2018
 */
public class DetailActivity extends AppCompatActivity {
    private ArrayList<Product> data = new ArrayList<>();
    private ArrayList<ProductDetail> list = new ArrayList<>();
    private ProductDAO dao;
    private CartDAO cartDAO;
    int pos;
    String id, nam, pric, detaill, image;
    String scrDt, scrResDt, frCamDt, reCamDt, cpuDt, ramDt,romDt, simDt, mCardDt, battCapDt, osDt;
    TextView name, price, detail, scr, scrRes, frCam, reCam, cpu, ram ,rom, sim, mCard, battCap, os;
    Button btnBuy, btnAddCart;
    ImageView prod, imvBack, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dao = new ProductDAO(getBaseContext());
//        dao.viewDetail(id);

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
                cartDAO.insertCart(id);
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
        detaill = data.get(pos).getSummary();

        //get data2
//        scrDt = list.get(scr).toString();
//        scrResDt = data2.get(pos).getScrRes();
//        frCamDt = data2.get(pos).getFrCam();
//        reCamDt = data2.get(pos).getReCam();
//        cpuDt = data2.get(pos).getCpu();
//        ramDt = data2.get(pos).getRam();
//        romDt = data2.get(pos).getRom();
//        simDt = data2.get(pos).getSim();
//        mCardDt = data2.get(pos).getmCard();
//        battCapDt = data2.get(pos).getBattCap();
//        osDt = data2.get(pos).getOs();

        //set data to textview and imageview
        Glide.with(getBaseContext()).load(image).into(prod);
        name.setText(nam);
        price.setText(pric);
        detail.setText(detaill);

//        scr.setText(scrDt);
//        scrRes.setText(scrResDt);
//        frCam.setText(frCamDt);
//        reCam.setText(reCamDt);
//        cpu.setText(cpuDt);
//        ram.setText(ramDt);
//        rom.setText(romDt);
//        sim.setText(simDt);
//        mCard.setText(mCardDt);
//        battCap.setText(battCapDt);
//        os.setText(osDt);
    }
}
