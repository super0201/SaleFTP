package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created By JohnNguyen - Onesoft on 16/12/2018
 */
public class CartDB extends SQLiteOpenHelper {

    public CartDB(Context context) {
        super(context, "Cart", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCart = "CREATE TABLE Cart (ID text primary key," + "Name text, Price integer, Amount integer, Image text)";
        db.execSQL(sqlCart);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
