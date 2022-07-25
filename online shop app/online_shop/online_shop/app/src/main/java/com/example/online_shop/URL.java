package com.example.online_shop;

public class URL {

//    public static String globalURL = "192.168.141.160";
    public static String IP = "192.168.0.0";
    public static String ROOT_URL = "http://"+IP+"/4210EA/online_shop/";
    public static String URL_LOGIN = ROOT_URL + "Login_php/login.php";
    public static String URL_REGISTER = ROOT_URL + "Login_php/register.php";
    public static String URL_PRODUCT_RETRIEVE = ROOT_URL + "Products_php/retrieve.php";
    public static String URL_PRODUCT_UPLOAD = ROOT_URL + "Products_php/upload.php";
    public static String URL_SHOPPING_ORDER = ROOT_URL + "Shopping_carts_php/order.php";
    public static String URL_SHOPPING_UPLOAD = ROOT_URL + "Shopping_carts_php/upload.php";
    public static String URL_SHOPPING_RETRIEVE = ROOT_URL + "Shopping_carts_php/retrieve.php";
    public static String URL_SHOPPING_DELETE = ROOT_URL + "Shopping_carts_php/delete.php";


    public static void updateIPAddress(){
        URL.ROOT_URL ="http://"+URL.IP+"/4210EA/online_shop/";
        URL.URL_LOGIN               = URL.ROOT_URL + "Login_php/login.php";
        URL.URL_REGISTER            = URL.ROOT_URL + "Login_php/register.php";
        URL.URL_PRODUCT_RETRIEVE    = URL.ROOT_URL + "Products_php/retrieve.php";
        URL.URL_PRODUCT_UPLOAD      = URL.ROOT_URL + "Products_php/upload.php";
        URL.URL_SHOPPING_ORDER      = URL.ROOT_URL + "Shopping_carts_php/order.php";
        URL.URL_SHOPPING_UPLOAD     = URL.ROOT_URL + "Shopping_carts_php/upload.php";
        URL.URL_SHOPPING_RETRIEVE   = URL.ROOT_URL + "Shopping_carts_php/retrieve.php";
        URL.URL_SHOPPING_DELETE     = URL.ROOT_URL + "Shopping_carts_php/delete.php";
    }
}
