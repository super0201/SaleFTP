package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {
    public UserDB(Context context) {
        super(context, "User", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUser = "CREATE TABLE User(Username text primary key , Name text, Password text, Phone text, Address text, Email text)";
        db.execSQL(sqlUser);

        sqlUser = "Insert Into User values ('admin','Admin','12345678', '0796181953', 'Quận 7', 'super20411@gmail.com')";
        db.execSQL(sqlUser);

        sqlUser = "Insert Into User values ('super0201','Guest','12345678', '089274721', 'Quận 9', 'super11024@gmail.com')";
        db.execSQL(sqlUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
