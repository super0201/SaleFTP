package com.team2.saleftp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private ImageView imvInfo;
    private TextInputEditText tiedtName, tiedtPhone, tiedtAddress;
    private ListView lvOrder;
    private TextView tvAmount, tvTotal;
    private Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("TIẾN HÀNH ĐẶT HÀNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void analyze() {
        
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
