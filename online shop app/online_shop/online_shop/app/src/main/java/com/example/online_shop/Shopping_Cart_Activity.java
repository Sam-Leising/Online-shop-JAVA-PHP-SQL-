package com.example.online_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.online_shop.adapter.Shopping_Cart_Adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shopping_Cart_Activity extends AppCompatActivity {

    ListView listView;
    Shopping_Cart_Adapter adapter;
    public static ArrayList<Product> productArrayList = new ArrayList<>();
    String url = "http://192.168.45.216/4210EA/online_shop/Shopping_carts_php/retrieve.php";
    Product product;
    BottomNavigationView bottomNavigationView;
    int Total;
    TextView txt_Total;


    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        listView = findViewById(R.id.myListView);
        adapter = new Shopping_Cart_Adapter(this,productArrayList);
        listView.setAdapter(adapter);
        txt_Total = findViewById(R.id.txt_Total);

        swipeRefreshLayout=findViewById(R.id.refresh);
        retrieveData();


        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.shoppingcard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
//                        overridePendingTransition(R.anim.left_in,1);
                        finish();
                        return true;

                    case R.id.shoppingcard:
                        return true;
                }

                return false;
            }
        });


        //============================================//
        //=============onclick show List==============//
        //============================================//

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View","Delete Data"};
                builder.setTitle(productArrayList.get(position).getTitle());
//                builder.setMessage("What do you want?");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
//
                                startActivity(new Intent(getApplicationContext(),ProductCart_Detail_Activity.class)
                                        .putExtra("position",position));

                                break;

                            case 1:

                                deleteData(productArrayList.get(position).getId());

                                break;

                        }



                    }
                });


                builder.create().show();


            }
        });

        //============================================/ /
        //=============onclick show List==============//
        //============================================//


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Total=0;
                retrieveData();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }




    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.45.216/4210EA/online_shop/Shopping_carts_php/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(Shopping_Cart_Activity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Shopping_Cart_Activity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Shopping_Cart_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }



    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        productArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("shopping_carts");

                            if(sucess.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String title = object.getString("title");
                                    String describe = object.getString("describe");
                                    String price = object.getString("price");
                                    String photo = object.getString("photo");
                                    String owner = object.getString("owner");
                                    String phone = object.getString("phone");

                                    product = new Product(id,title,describe,price,photo,owner,phone);
                                    productArrayList.add(product);
                                    adapter.notifyDataSetChanged();

                                    String t = object.getString("price");
                                    int total=Integer.valueOf(t).intValue();
                                    Total =Total+total;

                                }
                                String s = Integer.toString(Total);
                                txt_Total.setText("Total:$"+s);


                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Shopping_Cart_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Product_Add_Activity.class));
    }
}


