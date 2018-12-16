package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import database.ProductDB;
import model.Product;
import model.ProductDetail;

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
        String sql = "Select * From Product";
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

    public ArrayList<ProductDetail> viewDetail(String ID){
        ArrayList<ProductDetail> list2 = new ArrayList<>();
        SQLiteDatabase mydb = db.getReadableDatabase();
        String sql = "SELECT * FROM " + "Detail" + " WHERE ID = '" + ID + "'";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (cs.isAfterLast() == false){
            ProductDetail fm = null;
                String scr = cs.getString(1);
                String scrRes = cs.getString(2);
                String frCam = cs.getString(3);
                String reCam = cs.getString(4);
                String cpu = cs.getString(5);
                String ram = cs.getString(6);
                String rom = cs.getString(7);
                String sim = cs.getString(8);
                String mCard = cs.getString(9);
                String battCap = cs.getString(10);
                String os = cs.getString(11);
            fm = new ProductDetail(scr, scrRes, frCam, reCam, cpu, ram, rom, sim, mCard, battCap, os);
            list2.add(fm);
            cs.moveToNext();
        }
        cs.close();
        return list2;
    }
}
