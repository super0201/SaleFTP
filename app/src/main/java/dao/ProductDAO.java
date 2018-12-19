package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import database.CartDB;
import database.ProductDB;
import model.Cart;
import model.Product;
import model.ProductDetail;

/**
 * Created By JohnNguyen - Onesoft on 03/12/2018
 */
public class ProductDAO {
    Context ct;
    ProductDB db;
    CartDB cartDB;

    public ProductDAO(Context context) {
        this.ct = context;
        db = new ProductDB(ct);
        cartDB = new CartDB(ct);
    }

    public ArrayList<Product> viewAll(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase mydb = db.getReadableDatabase();
        String sql = "Select * From Product";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Product fm = null;
            String id = cs.getString(0);
            String nam = cs.getString(1);
            Integer price = cs.getInt(2);
            String detail = cs.getString(3);
            String sum = cs.getString(4);
            String image = cs.getString(5);
            fm = new Product(nam, sum, image, id, detail, price);
            list.add(fm);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }

    public ProductDetail viewDetail(String ID){
        ProductDetail pd = null;
        SQLiteDatabase mydb = db.getReadableDatabase();
        String sql = "SELECT * FROM Detail WHERE ID = '"+ ID +"'";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            pd = new ProductDetail();
                pd.setId(cs.getString(0));
                pd.setScr(cs.getString(1));
                pd.setScrRes(cs.getString(2));
                pd.setFrCam(cs.getString(3));
                pd.setReCam(cs.getString(4));
                pd.setCpu(cs.getString(5));
                pd.setRam(cs.getString(6));
                pd.setRom(cs.getString(7));
                pd.setSim(cs.getString(8));
                pd.setmCard(cs.getString(9));
                pd.setBattCap(cs.getString(10));
                pd.setOs(cs.getString(11));
            break;
        }
        cs.close();
        return pd;
    }

    public long insertCart(String id, String Name, Integer Price, Integer Amount, String Image) {
        SQLiteDatabase mydb = cartDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("Name", Name);
        values.put("Price", Price);
        values.put("Amount", Amount);
        values.put("Image", Image);
        long inta = mydb.insert("Cart", null, values);
        return inta;
    }

    public ArrayList<Cart> viewAllCart(){
        ArrayList<Cart> list = new ArrayList<>();
        SQLiteDatabase mydb = cartDB.getReadableDatabase();
        String sql = "Select * From Cart";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Cart ca = null;
            String id = cs.getString(0);
            String nam = cs.getString(1);
            Integer price = cs.getInt(2);
            Integer amount = cs.getInt(3);
            String image = cs.getString(4);
            ca = new Cart(id, nam, image, amount, price);
            list.add(ca);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    public int deleteCart(String id){
        SQLiteDatabase mydb = cartDB.getReadableDatabase();
        int result = mydb.delete("Cart","id=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }
}
