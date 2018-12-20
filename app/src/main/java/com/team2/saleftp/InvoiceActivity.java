package com.team2.saleftp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.InvoiceAdapter;
import dao.InvoiceDAO;
import model.Invoice;

public class InvoiceActivity extends AppCompatActivity {

    private ArrayList<Invoice> listInvoice = new ArrayList<>();

    ListView lvInvoice;
    InvoiceAdapter invoiceAdapter;
    InvoiceDAO invoiceDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        lvInvoice = (ListView) findViewById(R.id.lvInvoice);

        invoiceDAO = new InvoiceDAO(getBaseContext());
        listInvoice = invoiceDAO.viewAllInvoice();

        invoiceAdapter = new InvoiceAdapter(getBaseContext(), listInvoice);
        lvInvoice.setAdapter(invoiceAdapter);

    }


    @Override protected void onResume() {
        super.onResume();
        listInvoice.clear();
        listInvoice = invoiceDAO.viewAllInvoice();
        invoiceAdapter.changeDataset(invoiceDAO.viewAllInvoice());
    }

}
