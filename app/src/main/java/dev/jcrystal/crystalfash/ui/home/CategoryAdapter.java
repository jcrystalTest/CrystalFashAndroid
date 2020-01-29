package dev.jcrystal.crystalfash.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.jcrystal.crystalfash.R;
import jcrystal.mobile.entities.enums.Categories;
import jcrystal.mobile.net.controllers.ManagerProduct;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context mContext ;
    private List<String> mData ;
    private HomeFragment fragment;

    public CategoryAdapter(Context mContext, List<String> mData, HomeFragment fragment) {
        this.mContext = mContext;
        this.mData = mData;
        this.fragment = fragment;
    }
    public void updateList(List<String> mData){
        this.mData = mData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.MyViewHolder holder, int position) {
        holder.txtCategory.setText(mData.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categories category = Categories.KIDS;
                if (holder.txtCategory.getText().toString().equals(Categories.MEN.getName())){
                    category = Categories.MEN;
                }else if (holder.txtCategory.getText().toString().equals(Categories.WOMEN.getName())){
                    category = Categories.WOMEN;
                }
                fragment.updateProductsByCategory(category);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCategory;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtCategory = (TextView) itemView.findViewById(R.id.txt_category) ;
            cardView = (CardView) itemView.findViewById(R.id.cardview_category_id);


        }
    }
}
