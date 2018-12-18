package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team2.saleftp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Invoice;

public class InvoiceAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Invoice> arrInvoice;
    public InvoiceAdapter(Context context, ArrayList<Invoice> arrInvoice) {
        super();
        this.context = context;
        this.arrInvoice = arrInvoice;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public static class ViewHolder {
        TextView tvNameIn, tvCodeIn, tvDatIn, tvSttIn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InvoiceAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_invoice, null);
            holder.tvNameIn = (TextView) convertView.findViewById(R.id.tvNameInvoice);
            holder.tvCodeIn = (TextView) convertView.findViewById(R.id.tvCodeInvoice);
            holder.tvDatIn = (TextView) convertView.findViewById(R.id.tvDatInvoice);
            holder.tvSttIn = (TextView) convertView.findViewById(R.id.tvSttInvoice);
        } else {
            holder = (InvoiceAdapter.ViewHolder) convertView.getTag();
        }

        Invoice invoice = arrInvoice.get(position);
        holder.tvNameIn.setText(invoice.getName());
        holder.tvCodeIn.setText(invoice.getCode());
        holder.tvDatIn.setText(GetDateX.getDateString(invoice.getDate()));
        holder.tvSttIn.setText(invoice.getStt());


        return convertView;
    }
}
