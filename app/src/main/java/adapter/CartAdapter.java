package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team2.saleftp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Cart;
import model.Product;

public class CartAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Cart> arrCart;
    ArrayList<Product> products;
    Context ct;

    public CartAdapter(Activity activity, ArrayList<Cart> arrCart, Context ct, ArrayList<Product> products) {
        this.activity = activity;
        this.arrCart = arrCart;
        this.ct = ct;
        this.products = products;

    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView tvName, tvPrice;
        public ImageView imvCart;
        public Button btnMinus, btnAmount, btnPlus;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cart, null);
            holder.tvName = view.findViewById(R.id.tvNameCart);
            holder.tvPrice = view.findViewById(R.id.tvPriceCart);
            holder.imvCart = view.findViewById(R.id.imvCart);
            holder.btnMinus = view.findViewById(R.id.btnMinus);
            holder.btnAmount = view.findViewById(R.id.btnAmount);
            holder.btnPlus = view.findViewById(R.id.btnPlus);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        holder.tvName.setText(cart.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPrice.setText(decimalFormat.format(cart.getPrice()) + "Đ");
        Glide.with(ct).load(products.get(i).getImage()).into(holder.imvCart);
        holder.btnAmount.setText(cart.getAmount() + "");
        return view;
    }
}
