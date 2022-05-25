package com.example.online_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.online_shop.adapter.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static String gobalURL = "192.168.166.160";
    public static ArrayList<Product> productArrayList = new ArrayList<>();
    String url = "http://"+MainActivity.gobalURL+"/4210EA/online_shop/Products_php/retrieve.php";
    Product product;

    BottomNavigationView bottomNavigationView;

    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,productArrayList);
        listView.setAdapter(adapter);


        swipeRefreshLayout=findViewById(R.id.refresh);

        //============================================//
        //==========only onclick show detail==========//
        //============================================//
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                startActivity(new Intent(getApplicationContext(),Product_Detail_Activity.class)
                        .putExtra("position",position));
            }

        });
        //============================================//
        //==========only onclick show detail==========//
        //============================================//
        retrieveData();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.shoppingcard:
                        startActivity(new Intent(getApplicationContext(),
                                Shopping_Cart_Activity.class));
//                        overridePendingTransition(R.anim.right_in,1);
                        finish();
                        return true;

                    case R.id.home:
                        return true;
                }

                return false;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveData();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




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
                            JSONArray jsonArray = jsonObject.getJSONArray("products");

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

                                }

                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Product_Add_Activity.class));
    }
}