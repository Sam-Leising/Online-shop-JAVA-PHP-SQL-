package com.example.online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_shop.Champions_LayoutManager;
import com.example.online_shop.Product;
import com.example.online_shop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Product> {
    Context context;
    List<Product> arrayListProducts;

    public MyAdapter(@NonNull Context context, List<Product> arrayListProducts) {
        super(context, R.layout.product_list_item,arrayListProducts);

        this.context = context;
        this.arrayListProducts = arrayListProducts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,null,true);

        TextView Product_Title = view.findViewById(R.id.txt_title);
        TextView Product_Price = view.findViewById(R.id.txt_price);
        ImageView Product_Photo = view.findViewById(R.id.txt_photo);

        Product_Title.setText(arrayListProducts.get(position).getTitle());
        Product_Price.setText(arrayListProducts.get(position).getPrice());

        Picasso
                .with(context)
                .load(arrayListProducts.get(position).getPhoto())
                .into(Product_Photo);

        return view;
    }


}