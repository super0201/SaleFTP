package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.team2.saleftp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.InvoiceDAO;
import dao.ProductDAO;
import model.Invoice;

public class InvoiceAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Invoice> arrInvoice;
    private InvoiceDAO invoiceDAO;

    public InvoiceAdapter(Context context, ArrayList<Invoice> arrInvoice) {
        super();
        this.context = context;
        this.arrInvoice = arrInvoice;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        invoiceDAO = new InvoiceDAO(context);
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
        ImageView imvDeleteInvocie;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        InvoiceAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_invoice, null);
            holder.tvNameIn = (TextView) convertView.findViewById(R.id.tvNameInvoice);
            holder.tvCodeIn = (TextView) convertView.findViewById(R.id.tvCodeInvoice);
            holder.tvDatIn = (TextView) convertView.findViewById(R.id.tvDatInvoice);
            holder.tvSttIn = (TextView) convertView.findViewById(R.id.tvSttInvoice);
            holder.imvDeleteInvocie = (ImageView) convertView.findViewById(R.id.imvDeleteInvoice);
            holder.imvDeleteInvocie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setMessage("Are You Sure You Want to Delete This Invoice?!")
                            .setTitle("Attempt to Delete A Invoice")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        invoiceDAO.dltInvoice(arrInvoice.get(position).getCode());
                                        arrInvoice.remove(position);
                                        notifyDataSetChanged();

                                        Toast.makeText(context, "ooooooh No!!", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "Good Choice", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            });

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

    @Override public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(ArrayList<Invoice> items){
        this.arrInvoice = items;
        notifyDataSetChanged();
    }

}
