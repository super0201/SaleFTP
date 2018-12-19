package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.team2.saleftp.CartActivity;
import com.team2.saleftp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dao.ProductDAO;
import model.Cart;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Cart> data;
    ProductDAO productDAO;


    public CartAdapter(Context context, ArrayList<Cart> data) {
        this.context = context;
        this.data = data;
        productDAO = new ProductDAO(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_cart, parent, false);
        viewHolder = new CartAdapter.MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Cart cart = data.get(position);
        MyItemHolder myItemHolder = (MyItemHolder) holder;

        myItemHolder.tvName.setText(cart.getName());
        Integer i = cart.getPrice();
        myItemHolder.tvPrice.setText(String.valueOf(i));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        myItemHolder.tvPrice.setText(decimalFormat.format(i)+ "Đ");
        Glide.with(context).load(data.get(position).getImage())
                .thumbnail(0.4f)
                .into(((MyItemHolder) holder).imvCart);
        myItemHolder.imvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDAO.deleteCart(data.get(position).getIdproduct());
                data.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView imvCart, imvDel;
        TextView tvName;
        TextView tvPrice;
        TextView tvAmount;
        Button  btnPlus, btnMinus;
        Cart cart;
        ProductDAO productDAO;
        int amount = 1;


        public MyItemHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameCart);
            imvCart = itemView.findViewById(R.id.imvCart);
            tvPrice = itemView.findViewById(R.id.tvPriceCart);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            imvDel = itemView.findViewById(R.id.imvDel);
            tvAmount.setText(""+amount);

            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount++;
                    tvAmount.setText(""+ amount);
                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(amount > 0)
                    {
                        amount--;
                        tvAmount.setText("" + amount);
                    }
                }
            });
        }
    }
}

//        Cart c = arrCart.get(i);
//        viewHolder.tvName.setText(c.getName());
////        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
////        viewHolder.tvPrice.setText(decimalFormat.format(cart.getPrice()) + "Đ");
//
//        Glide.with(ct).load(c.getImage()).into(viewHolder.imvCart);

//        viewHolder.btnAmount.setText(cart.getAmount() + "");

//        int sl = Integer.parseInt(viewHolder.btnAmount.getText().toString());
//        if(sl>10){
//            viewHolder.btnPlus.setVisibility(View.INVISIBLE);
//            viewHolder.btnMinus.setVisibility(View.VISIBLE);
//        }else if(sl <=1){
//            viewHolder.btnMinus.setVisibility(View.INVISIBLE);
//        }else if(sl >= 1){
//            viewHolder.btnMinus.setVisibility(View.VISIBLE);
//            viewHolder.btnPlus.setVisibility(View.VISIBLE);
//        }

//        final Button btnplus = viewHolder.btnPlus;
//        final Button btnminus = viewHolder.btnMinus;
//        final Button btnamount = viewHolder.btnAmount;
//        final TextView tvnewprice = viewHolder.tvPrice;
//
//        btnplus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int newamount = Integer.parseInt(btnamount.getText().toString()) +1;
//                int nowamount = DetailActivity.arrCart.get(i).getAmount();
//                double pricenow = DetailActivity.arrCart.get(i).getPrice();
//
//                DetailActivity.arrCart.get(i).setAmount(newamount);
//                double newprice = (pricenow * newamount) / nowamount;
//
//                DetailActivity.arrCart.get(i).setPrice(newprice);
//                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//
//                tvnewprice.setText(decimalFormat.format(newprice) + "Đ");
//                CartActivity.Event();
//
//                if(newamount > 9){
//                    btnplus.setVisibility(View.INVISIBLE);
//                    btnminus.setVisibility(View.VISIBLE);
//                    btnamount.setText(String.valueOf(newamount));
//                }else {
//                    btnminus.setVisibility(View.VISIBLE);
//                    btnplus.setVisibility(View.VISIBLE);
//                    btnamount.setText(String.valueOf(newamount));
//                }
//            }
//        });
//
//        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int newamount = Integer.parseInt(btnamount.getText().toString()) +1;
//                int nowamount = DetailActivity.arrCart.get(i).getAmount();
//                double pricenow = DetailActivity.arrCart.get(i).getPrice();
//
//                DetailActivity.arrCart.get(i).setAmount(newamount);
//                double newprice = (pricenow * newamount) / nowamount;
//                DetailActivity.arrCart.get(i).setPrice(newprice);
//
//                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                tvnewprice.setText(decimalFormat.format(newprice) + "Đ");
//                CartActivity.Event();
//
//                if(newamount < 2){
//                    btnminus.setVisibility(View.INVISIBLE);
//                    btnplus.setVisibility(View.VISIBLE);
//                    btnamount.setText(String.valueOf(newamount));
//                }else {
//                    btnminus.setVisibility(View.VISIBLE);
//                    btnplus.setVisibility(View.VISIBLE);
//                    btnamount.setText(String.valueOf(newamount));
//                }
//            }
//        });


