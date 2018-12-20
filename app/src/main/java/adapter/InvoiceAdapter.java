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

import com.team2.saleftp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dao.InvoiceDAO;
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
        TextView tvNameIn, tvAmountIn, tvTotalIn, tvCodeIn, tvDatIn, tvSttIn;
        ImageView imvDeleteInvocie;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.item_invoice, null);
            holder.tvNameIn = (TextView) convertView.findViewById(R.id.tvNameInvoice);
            holder.tvAmountIn = (TextView) convertView.findViewById(R.id.tvAmountInvoice);
            holder.tvTotalIn = (TextView) convertView.findViewById(R.id.tvTotalInvoice);
            holder.tvCodeIn = (TextView) convertView.findViewById(R.id.tvCodeInvoice);
            holder.tvDatIn = (TextView) convertView.findViewById(R.id.tvDatInvoice);
            holder.tvSttIn = (TextView) convertView.findViewById(R.id.tvSttInvoice);
            holder.imvDeleteInvocie = (ImageView) convertView.findViewById(R.id.imvDeleteInvoice);

            holder.imvDeleteInvocie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(v.getRootView().getContext())
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

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }
            });

            convertView.setTag(holder);

        } else {
            holder = (InvoiceAdapter.ViewHolder) convertView.getTag();
        }

        Invoice invoice = (Invoice) arrInvoice.get(position);

        String x = invoice.getName();
        x = x.substring(0, x.length() -1);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.tvNameIn.setText("Đơn hàng: " + x);
        holder.tvAmountIn.setText("Số lượng: " + invoice.getAmount());
        holder.tvTotalIn.setText("Tổng giá: " + decimalFormat.format(invoice.getTotal()) + "Đ");
        holder.tvCodeIn.setText("Mã đơn hàng: " + invoice.getCode());
        holder.tvDatIn.setText("Ngày mua: " + invoice.getDate());
        holder.tvSttIn.setText("Trạng thái: " + invoice.getStt());


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
