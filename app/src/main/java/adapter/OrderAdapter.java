package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team2.saleftp.R;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Cart;
import model.Product;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    ArrayList<Cart> arrCart;
    ProductDAO dao;

    public OrderAdapter(Context context, ArrayList<Cart> arrayCart) {
        super();
        this.context = context;
        this.arrCart = arrayCart;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        TextView tvNameP, tvAmountP, tvPriceP;
        ImageView imvIconP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_order, null);
            holder.imvIconP = (ImageView) convertView.findViewById(R.id.imvPOrder);
            holder.tvNameP = (TextView) convertView.findViewById(R.id.tvNamePOrder);
            holder.tvAmountP = (TextView) convertView.findViewById(R.id.tvAmountPOrder);
            holder.tvPriceP = (TextView) convertView.findViewById(R.id.tvPricePOrder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cart cart = arrCart.get(position);
        holder.tvNameP.setText(cart.getName());
        holder.tvAmountP.setText(cart.getAmount());
        holder.tvPriceP.setText(String.valueOf(cart.getPrice()));

        Glide.with(context).load(arrCart.get(position).getIdproduct())
                .into(holder.imvIconP);

        return convertView;
    }

}
