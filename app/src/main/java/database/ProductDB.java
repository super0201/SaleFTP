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

        sqlProd = "Insert Into Product values ( 'SM021','Samsung Galaxy Note 9','17.000.000 Đ', '', 'Flagship hàng đầu của Samsung ra mắt', 'https://i.imgur.com/gru5CzF.jpg')";
        db.execSQL(sqlProd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
