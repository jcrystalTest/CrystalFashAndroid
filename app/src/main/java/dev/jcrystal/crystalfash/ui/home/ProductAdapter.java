package dev.jcrystal.crystalfash.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import dev.jcrystal.crystalfash.R;
import dev.jcrystal.crystalfash.ui.product.ProductActivity;
import jcrystal.mobile.entities.ProductNormal;
import jcrystal.mobile.entities.enums.Categories;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context mContext ;
    private List<ProductNormal> mData ;


    public ProductAdapter(Context mContext, List<ProductNormal> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_product,parent,false);
        return new MyViewHolder(view);
    }

    public void updateList(List<ProductNormal> mData){
        this.mData = mData;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtName.setText(mData.get(position).name());
        holder.txtCategory.setText(mData.get(position).category().name());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.txtPrice.setText(formatter.format(mData.get(position).price()));
        holder.txtOldPrice.setText(formatter.format(mData.get(position).oldPrice()));

        Picasso.get().load(mData.get(position).image()).into(holder.imgProduct);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ProductActivity.class);

                intent.putExtra("idProduct",mData.get(position).id());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName,txtCategory, txtPrice, txtOldPrice;
        ImageView imgProduct;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.product_name_id) ;
            txtCategory = (TextView) itemView.findViewById(R.id.product_category_id) ;
            txtPrice = (TextView) itemView.findViewById(R.id.product_price_id) ;
            txtOldPrice = (TextView) itemView.findViewById(R.id.product_old_price_id) ;
            imgProduct = (ImageView) itemView.findViewById(R.id.product_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}
