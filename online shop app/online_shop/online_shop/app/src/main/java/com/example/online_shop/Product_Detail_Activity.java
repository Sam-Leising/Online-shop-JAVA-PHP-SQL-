package com.example.online_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Product_Detail_Activity extends AppCompatActivity {
    TextView title,describe,price,txt_photo,txt_id,owner,phone;
    ImageView photo,back;
    Button addToCart,btn_buy;
    int position;

    private static final String url="http://192.168.45.216/4210EA/online_shop/Shopping_carts_php/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        txt_id = findViewById(R.id.txt_id);
        title = findViewById(R.id.txt_title);
        describe = findViewById(R.id.txt_describe);
        price = findViewById(R.id.txt_price);
        txt_photo=findViewById(R.id.txt_photo);
        photo = findViewById(R.id.product_photo);
        owner = findViewById(R.id.txt_owner);
        phone = findViewById(R.id.txt_phone);
        addToCart=(Button)findViewById(R.id.btn_addToCart);



        back = findViewById(R.id.imageView9);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//==========================================================================
//        confirm to buy
//===========================================================================
        btn_buy=(Button)findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buy_id = txt_id.getText().toString();
                String buy_title = title.getText().toString();
                String buy_price =price.getText().toString() ;
                String buy_photo = txt_photo.getText().toString();
                Intent intent =Product_Buy_Activity.newIntent(Product_Detail_Activity.this,buy_id,buy_title,buy_price,buy_photo);
                startActivity(intent);
            }
        });
//===========================================================================

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
                finish();
            }
        });

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        txt_id.setText(MainActivity.productArrayList.get(position).getId());
        title.setText(MainActivity.productArrayList.get(position).getTitle());
        describe.setText(MainActivity.productArrayList.get(position).getDescribe());
        price.setText(MainActivity.productArrayList.get(position).getPrice());
        txt_photo.setText(MainActivity.productArrayList.get(position).getPhoto());
        owner.setText(MainActivity.productArrayList.get(position).getOwner());
        phone.setText(MainActivity.productArrayList.get(position).getPhone());
        Picasso
                .with(Product_Detail_Activity.this)
                .load(MainActivity.productArrayList.get(position).getPhoto())
                .into(photo);

    }

    private void insertData()
    {
        final String ID = txt_id.getText().toString().trim();
        final String Title=title.getText().toString().trim();
        final String Describe=describe.getText().toString().trim();
        final String Price=price.getText().toString().trim();
        final String txt_Photo=txt_photo.getText().toString().trim();
        final String Owner=owner.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                txt_id.setText("");
                title.setText("");
                describe.setText("");
                price.setText("");
                txt_photo.setText("");
                owner.setText("");
                phone.setText("");
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map=new HashMap<String, String>();
                map.put("txt_id",ID);
                map.put("title",Title);
                map.put("describe",Describe);
                map.put("price",Price);
                map.put("photo",txt_Photo);
                map.put("owner",Owner);
                map.put("phone",Phone);

                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


}