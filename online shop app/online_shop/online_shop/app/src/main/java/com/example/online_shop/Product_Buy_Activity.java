package com.example.online_shop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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

public class Product_Buy_Activity extends AppCompatActivity {
    TextView txt_id,txt_title,txt_price;
    ImageView txt_photo,back;
    TextView buyer_Name, buyer_Phone, buyer_Location;
    Button order_upload;

    private static final String url="http://"+MainActivity.gobalURL+"/4210EA/online_shop/Shopping_carts_php/order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_buy);

        txt_id =(TextView)findViewById(R.id.txt_id);
        txt_title =(TextView)findViewById(R.id.txt_title);
        txt_price =(TextView)findViewById(R.id.txt_price);
        txt_photo = (ImageView) findViewById(R.id.txt_photo);
        buyer_Name=(TextView)findViewById(R.id.buyer_Name);
        buyer_Phone=(TextView)findViewById(R.id.buyer_Phone);
        buyer_Location=(TextView)findViewById(R.id.buyer_Location);


        String buy_id = getIntent().getStringExtra("buy_id");
        String buy_title = getIntent().getStringExtra("buy_title");
        String buy_price = getIntent().getStringExtra("buy_price");
        String buy_photo = getIntent().getStringExtra("buy_photo");



        txt_id.setText(buy_id);
        txt_title.setText(buy_title);
        txt_price.setText(buy_price);

        Picasso
                .with(Product_Buy_Activity.this)
                .load(buy_photo)
                .into(txt_photo);



        order_upload=(Button)findViewById(R.id.order_upload);
        order_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showNormalDialog();
//                insertData();
//                finish();
            }
        });

        back = findViewById(R.id.imageView9);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void showNormalDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(Product_Buy_Activity.this);
//        normalDialog.setTitle("");
        normalDialog.setMessage("Do you really want to buy?");
        normalDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertData();
                        finish();
                    }
                });
        normalDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }


    public static Intent newIntent(Context packageContext,String buy_id, String buy_title, String buy_price,String buy_photo){
        Intent intent = new Intent(packageContext,Product_Buy_Activity.class);
        intent.putExtra("buy_id",buy_id);
        intent.putExtra("buy_title",buy_title);
        intent.putExtra("buy_price",buy_price);
        intent.putExtra("buy_photo",buy_photo);
        return intent;
    }

    private void insertData()
    {
        final String ID = txt_id.getText().toString().trim();
        final String Title=txt_title.getText().toString().trim();
        final String Price=txt_price.getText().toString().trim();
        final String Name=buyer_Name.getText().toString().trim();
        final String Phone=buyer_Phone.getText().toString().trim();
        final String Location=buyer_Location.getText().toString().trim();


        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                txt_id.setText("");
                txt_title.setText("");
                txt_price.setText("");
                buyer_Name.setText("");
                buyer_Phone.setText("");
                buyer_Location.setText("");
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
                map.put("product_id",ID);
                map.put("title",Title);
                map.put("buyer",Name);
                map.put("phone",Phone);
                map.put("location",Location);
                map.put("price",Price);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}