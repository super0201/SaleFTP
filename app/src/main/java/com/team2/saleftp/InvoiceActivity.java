package com.team2.saleftp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
}
