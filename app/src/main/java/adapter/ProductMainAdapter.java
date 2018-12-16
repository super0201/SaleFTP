package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team2.saleftp.R;

import java.util.ArrayList;

import model.Product;

public class ProductMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Product> data;

    public ProductMainAdapter(Context context, ArrayList<Product> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product, parent, false);
        viewHolder = new ProductMainAdapter.MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product Product = data.get(position);
        MyItemHolder myItemHolder = (MyItemHolder) holder;
        myItemHolder.tvName.setText(Product.getName());
        myItemHolder.tvPrice.setText(Product.getPrice());

        Glide.with(context)
                .load(data.get(position).getImage())
                .asBitmap()
                .override(600, 300)
                .into(((MyItemHolder) holder).imvProduct);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView imvProduct;
        TextView tvName, tvPrice;

        public MyItemHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            imvProduct = (ImageView) itemView.findViewById(R.id.imvProduct);
        }
    }
}
