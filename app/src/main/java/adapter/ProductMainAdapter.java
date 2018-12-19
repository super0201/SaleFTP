package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team2.saleftp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Product;

/**
 * Created By JohnNguyen - Onesoft on 14/12/2018
 */
public class ProductMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Product> data;
    Context context;

    public ProductMainAdapter(Context context, ArrayList<Product> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product, parent, false);
        viewHolder = new ProductMainAdapter.MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product Product = data.get(position);
        MyItemHolder myItemHolder = (MyItemHolder) holder;
        myItemHolder.tvName.setText(Product.getName());
        Integer d = Product.getPrice();
        myItemHolder.tvPrice.setText(String.valueOf(d));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        myItemHolder.tvPrice.setText(decimalFormat.format(d));

        Glide.with(context)
                .load(data.get(position).getImage())
                .into(((MyItemHolder) holder).imvProduct);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView imvProduct;
        TextView tvName, tvPrice;

        private MyItemHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            imvProduct = (ImageView) itemView.findViewById(R.id.imvProduct);
        }
    }
}
