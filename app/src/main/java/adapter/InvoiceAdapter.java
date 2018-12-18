package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import dao.InvoiceDAO;
import model.Invoice;

public class InvoiceAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    ArrayList<Invoice> arrInvoice;
    InvoiceDAO dao;

    public InvoiceAdapter(Context context, LayoutInflater inflater, ArrayList<Invoice> arrInvoice, InvoiceDAO dao) {
        this.context = context;
        this.inflater = inflater;
        this.arrInvoice = arrInvoice;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return arrInvoice.size();
    }

    @Override
    public Object getItem(int position) {
        return arrInvoice.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
