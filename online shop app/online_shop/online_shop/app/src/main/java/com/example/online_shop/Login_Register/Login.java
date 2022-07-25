package com.example.online_shop.Login_Register;

import static android.content.ContentValues.TAG;
import static com.example.online_shop.URL.URL_LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.online_shop.Dialog.CustomDialogOneEditText;
import com.example.online_shop.Dialog.LoadingDialog;
import com.example.online_shop.MainActivity;
import com.example.online_shop.R;
import com.example.online_shop.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements CustomDialogOneEditText.Custom_DialogInterface{

    private EditText etEmail, etPassword;
    private String email,password;
    private TextView ipAddress;

    //共享參數 shared preferences
    SharedPreferences USER_PREF;
    private static final String SHARED_PREF_NAME = "currentUserPref";
    SharedPreferences IP_PREF;
    private static final String SHARED_PREF_NAME2 = "IPPref";

    //loading
    final LoadingDialog loadingDialog = new LoadingDialog(Login.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = password = "";
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ipAddress = findViewById(R.id.ipAddress);
        USER_PREF = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        IP_PREF = getSharedPreferences(SHARED_PREF_NAME2,MODE_PRIVATE);
        getNewIPAddress();
        autoLogin();


        ipAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogOneEditText customDialog = new CustomDialogOneEditText();
                customDialog.show(getSupportFragmentManager(),"Update IP Address：");
            }
        });
    }

    public void login(View view) {
        SharedPreferences.Editor editor = USER_PREF.edit();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        final Boolean autoLogin = USER_PREF.getBoolean("autoLogin",false);
        if(!email.equals("") && !password.equals("")){
            loadingDialog.startDialog();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    loadingDialog.dismissDialog();
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if(success.equals("1")){
                            JSONArray jsonArray = jsonObject.getJSONArray("users");

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                int id = object.getInt("id");
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String password = object.getString("password");

                                //shared Preferences store user information
                                editor.putInt("currentUserID", id);
                                editor.putString("currentUserName", name);
                                editor.putString("currentUserEmail", email);
                                editor.putString("currentUserPassword",password);
                                editor.putBoolean("autoLogin",true);
                                editor.apply();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }else if(success.equals("0")){
                            Toast.makeText(Login.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e) {
//                        e.printStackTrace();
                        //record error
                        //https://developer.android.com/reference/android/util/Log.html
                        Log.e(TAG,"other problem", e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this,error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email",email);
                    data.put("password",password);
                    data.put("autoLogin",autoLogin.toString());
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this,"Field can not be empty!",Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void applyTexts(String ipAddress) {
        SharedPreferences.Editor editor = IP_PREF.edit();
        this.ipAddress.setText(ipAddress);
        editor.putString("ipAddress",ipAddress);
        editor.apply();

        System.out.println("New IP Address: "+ this.ipAddress.getText());

        URL.IP = ipAddress;
        URL.updateIPAddress();
    }
    protected void getNewIPAddress(){
        IP_PREF.getString("ipAddress",URL.IP);
        URL.IP = IP_PREF.getString("ipAddress",URL.IP);
        URL.updateIPAddress();

        if (URL.IP == "192.168.0.0"){
            ipAddress.setText(IP_PREF.getString("ipAddress",URL.IP));
        }else{
            ipAddress.setText(IP_PREF.getString("ipAddress",URL.IP));
        }
        System.out.println("Ip Address is: "+ipAddress.getText().toString());
    }
    protected void autoLogin(){
        if(USER_PREF.getBoolean("autoLogin",false) == true){
            etEmail.setText(USER_PREF.getString("currentUserEmail","email"));
            etPassword.setText(USER_PREF.getString("currentUserPassword","password"));
            login(findViewById(R.id.button));
            System.out.println("This user auto login email: "+USER_PREF.getString("currentUserEmail","email")+",  password: "+USER_PREF.getString("currentUserPassword","password"));
        }
    }
}