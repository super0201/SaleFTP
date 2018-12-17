package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import database.CartDB;
import model.Product;

public class CartDAO {
    Context ct;
    CartDB cartDB;

    public CartDAO(Context ct, CartDB cartDB) {
        this.ct = ct;
        this.cartDB = cartDB;
    }

    public long insertCart(String id) {
        SQLiteDatabase mydb = cartDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", id);
        long inta = mydb.insert("Cart", null, values);
        return inta;
    }

    public ArrayList<Product> viewCart() {
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase mydb = cartDB.getReadableDatabase();
        String sql = "SELECT ID FROM Cart";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            Product fm = null;
            String id = cs.getString(0);
            String nam = cs.getString(1);
            String price = cs.getString(2);
            String detail = cs.getString(3);
            String sum = cs.getString(4);
            String image = cs.getString(5);
            fm = new Product(nam, price, detail, image, id, sum);
            list.add(fm);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
}
