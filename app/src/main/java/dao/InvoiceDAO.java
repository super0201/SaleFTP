package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import database.InvoiceDB;
import model.Invoice;

public class InvoiceDAO {
    Context ct;
    InvoiceDB invoiceDB;

    public InvoiceDAO(Context context) {
        this.ct = context;
        invoiceDB = new InvoiceDB(ct);
    }

    public long insertInvoice(String code, String name, int amount, double total, String dat, String stt) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SQLiteDatabase mydb = invoiceDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CODE", code);
        values.put("NAME", name);
        values.put("AMOUNT", amount);
        values.put("TOTAL", total);
        values.put("DAT", dat);
        values.put("STT", stt);

        long inv = mydb.insert("Invoice", null, values);
        return inv;
    }

    public int dltInvoice(String id) {
        SQLiteDatabase mydb = invoiceDB.getWritableDatabase();
        int result = mydb.delete("Invoice","CODE=?",new String[]{id});
        if (result == 0)
            return -1;
        return 1;

    }

    public ArrayList<Invoice> viewAllInvoice() {

        ArrayList<Invoice> list = new ArrayList<>();
        SQLiteDatabase mydb = invoiceDB.getReadableDatabase();
        String sql = "Select * From Invoice";
        Cursor cs = mydb.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            Invoice iv = null;
            String code = cs.getString(0);
            String name = cs.getString(1);
            int amount = cs.getInt(2);
            double total = cs.getInt(3);
            String dat = cs.getString(4);
            String stt = cs.getString(5);

            iv = new Invoice(code, name, dat, stt, amount, total);
            list.add(iv);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
}
