package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InvoiceDB extends SQLiteOpenHelper {

    public InvoiceDB(Context context) {
        super(context, "Invoice", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlInvoice = "CREATE TABLE Invoice (CODE TEXT PRIMARY KEY, NAME TEXT, AMOUNT INT, TOTAL DOUBLE, DAT DATE, STT TEXT)";
        db.execSQL(sqlInvoice);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
