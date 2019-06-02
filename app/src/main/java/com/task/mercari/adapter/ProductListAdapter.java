package com.task.mercari.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.task.mercari.R;
import com.task.mercari.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public class ProductListAdapter  extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>{

    List<Product> productList = new ArrayList<>();

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item_layout,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        if (productList != null){
            Product product = productList.get(position);

            holder.txtName.setText(product.getName());
            holder.txtComments.setText(String.valueOf(product.getNumComments()));
            holder.txtLikes.setText(String.valueOf(product.getNumLikes()));
            holder.txtPrice.setText(String.format("$ "+product.getPrice()));
            Glide.with(holder.imgProduct).load(product.getPhoto()).into(holder.imgProduct).clearOnDetach();

        }

    }

    @Override
    public int getItemCount() {
        return productList != null?productList.size():0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtPrice,txtLikes,txtComments;
        ImageView imgProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtComments = itemView.findViewById(R.id.txt_comments);
            txtLikes = itemView.findViewById(R.id.txt_likes);
            txtPrice = itemView.findViewById(R.id.txt_price);
            imgProduct = itemView.findViewById(R.id.img_product);

        }
    }
}
