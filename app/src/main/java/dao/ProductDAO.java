package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import database.ProductDB;
import model.Product;

/**
 * Created By JohnNguyen - Onesoft on 03/12/2018
 */
public class ProductDAO {
    Context ct;
    ProductDB db;


    public ProductDAO(Context context) {
        this.ct = context;
        db = new ProductDB(ct);
    }

    public ArrayList<Product> viewAll(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase mydb = db.getReadableDatabase();
        String sql = "Select * From Summary";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            Product fm = null;
            String id = cs.getString(0);
            String nam = cs.getString(1);
            Double price = cs.getDouble(2);
            String sum = cs.getString(3);
            String image = cs.getString(4);
            Integer amount = cs.getInt(5);

            fm = new Product(id, price, nam, sum, image,amount);
            list.add(fm);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
}
