package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created By JohnNguyen - Onesoft on 11/12/2018
 */
public class ProductDB extends SQLiteOpenHelper {

    public ProductDB(Context context) {
        super(context, "Product", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlProd = "CREATE TABLE Product(ID text primary key," + "Name text, Price text, Detail text, Summary text, Image text)";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SM021','Samsung Galaxy Note 9','17.000.000 Đ', 'Sản phẩm mới nhất đến từ Samsung, sản phẩmm high-end', 'Flagship hàng đầu của Samsung ra mắt', 'https://i.imgur.com/gru5CzF.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'XA012','Xiaomi Mi Mix 3','16.000.000 Đ', 'Sản phẩm mới nhất đến từ Samsung, sản phẩmm high-end', 'Flagship hàng đầu của Xiaomi ra mắt', 'https://i.imgur.com/gje3Qrp.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'AP065','IPhone Xs Max','23.000.000 Đ', 'Sản phẩm mới nhất đến từ Samsung, sản phẩmm high-end', 'Flagship hàng đầu của Apple ra mắt', 'https://i.imgur.com/KtfZl9w.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'HW543','Huawei Mate 20 Pro','19.000.000 Đ', 'Sản phẩm mới nhất đến từ Samsung, sản phẩmm high-end', 'Flagship hàng đầu của Huawei ra mắt', 'https://i.imgur.com/0vR2t75.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SO435','Sony Xperia XZ2','16.000.000 Đ', 'Sản phẩm mới nhất đến từ Samsung, sản phẩmm high-end', 'Flagship hàng đầu của Sony ra mắt', 'https://i.imgur.com/WItCuxV.jpg')";
        db.execSQL(sqlProd);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
