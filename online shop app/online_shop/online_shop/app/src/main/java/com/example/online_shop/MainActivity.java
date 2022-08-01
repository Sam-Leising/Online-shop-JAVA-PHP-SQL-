package com.example.online_shop;

import static com.example.online_shop.URL.URL_PRODUCT_RETRIEVE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Product> productArrayList = new ArrayList<>();
    Product product;
    SharedPreferences IP_PREF;
    private static final String SHARED_PREF_NAME2 = "IPPref";

    BottomNavigationView bottomNavigationView;

    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,productArrayList);
        listView.setAdapter(adapter);
        IP_PREF = getSharedPreferences(SHARED_PREF_NAME2,MODE_PRIVATE);



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
        StringRequest request = new StringRequest(Request.Method.POST,URL_PRODUCT_RETRIEVE,
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
                                    System.out.println("photo url: "+photo);

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
                /*
                https://so.muouseo.com/qa/ve5oqgmym6oj.html
                在 android 11（API 级别 30）中 Compatibility.isChangeEnabled(CHANGE_TEXT_TOASTS_IN_THE_SYSTEM)
                将在 Toast 类 show() 方法中返回 true，对于 android 11，如 android developer site 中所述

                现在，如果您想跳过此异常，请不要在 Toast null 的消息参数中传递 Toast.makeText(applicationContext,
                obj.getString("message"),Toast.LENGTH_LONG).show() 此处您的 obj.getString("message") 在某些情况下为空，
                因此您检查 null 然后显示 Toast 如下
                 */
                if (error!=null && error.getMessage()!=null) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }else {retrieveData();}
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("ip",IP_PREF.getString("ipAddress",URL.IP));
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Product_Add_Activity.class));
    }
}