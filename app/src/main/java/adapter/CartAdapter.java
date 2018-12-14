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
import com.team2.saleftp.CartActivity;
import com.team2.saleftp.MainActivity;
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
        return arrCart.get(i);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cart, null);
            viewHolder.tvName = view.findViewById(R.id.tvNameCart);
            viewHolder.tvPrice = view.findViewById(R.id.tvPriceCart);
            viewHolder.imvCart = view.findViewById(R.id.imvCart);
            viewHolder.btnMinus = view.findViewById(R.id.btnMinus);
            viewHolder.btnAmount = view.findViewById(R.id.btnAmount);
            viewHolder.btnPlus = view.findViewById(R.id.btnPlus);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        viewHolder.tvName.setText(cart.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvPrice.setText(decimalFormat.format(cart.getPrice()) + "Đ");
        Glide.with(ct).load(products.get(i).getImage()).into(viewHolder.imvCart);
        viewHolder.btnAmount.setText(cart.getAmount());
        int sl = Integer.parseInt(viewHolder.btnAmount.getText().toString());
        if(sl>10){
            viewHolder.btnPlus.setVisibility(View.INVISIBLE);
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
        }else if(sl <=1){
            viewHolder.btnMinus.setVisibility(View.INVISIBLE);
        }else if(sl >= 1){
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
            viewHolder.btnPlus.setVisibility(View.VISIBLE);
        }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newamount = Integer.parseInt(finalViewHolder.btnAmount.getText().toString()) +1;
                int nowamount = MainActivity.arrCart.get(i).getAmount();
                double pricenow = MainActivity.arrCart.get(i).getPrice();
                MainActivity.arrCart.get(i).setAmount(newamount);
                double newprice = (pricenow * newamount) / nowamount;
                MainActivity.arrCart.get(i).setPrice(newprice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.tvPrice.setText(decimalFormat.format(newprice) + "Đ");
                CartActivity.Event();
                if(newamount > 9){
                    finalViewHolder.btnPlus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnAmount.setVisibility(Integer.parseInt(String.valueOf(newamount)));
                }else {
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnPlus.setVisibility(View.VISIBLE);
                }
            }
        });
        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        return view;
    }
}
