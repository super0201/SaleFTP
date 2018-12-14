package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import database.UserDB;
import model.User;

public class UserDAO {
    private static SQLiteDatabase db;
    private UserDB dbHelper;

    public UserDAO(Context context) {
        dbHelper = new UserDB(context);
//        db = dbHelper.getWritableDatabase();
//        db = dbHelper.getReadableDatabase();
    }
    //insert
    public long insertUser(String username, String pass, String name, String email, String addr, String phone){
        ContentValues values = new ContentValues();
        values.put("Username", username.toLowerCase());
        values.put("Password", pass);
        values.put("Email", email);
        values.put("Address", addr);
        values.put("Phone", phone);
        values.put("Name", name);
        long pos = db.insert("User",null, values);
        return pos;
    }

    //getAll
    public List<User> getAllUser() throws ParseException {
        db = dbHelper.getReadableDatabase();
        List<User> dsUser = new ArrayList<>();
        Cursor c = db.query("User",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            User ee = new User();
            ee.setUsername(c.getString(0));
            ee.setPassword(c.getString(2));
            ee.setName(c.getString(1));
            ee.setPhone(c.getString(3));
            ee.setAddr(c.getString(4));
            dsUser.add(ee);
            c.moveToNext();
        }
        c.close();
        return dsUser;
    }

    public int updateUser(String username, String name, String email, String addr, String phone){
        ContentValues values = new ContentValues();
        values.put("Username",username);
        values.put("Name",name);
        values.put("Email", email);
        values.put("Address",addr);
        values.put("Phone",phone);
        int result = db.update("User",values,"username=?", new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }

    public int changePasswordUser(User nd){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username",nd.getUsername());
        values.put("Password",nd.getPassword());
        int result = db.update("User",values,"username=?", new String[]{nd.getUsername()});
        if (result == 0){
            return -1;
        }
        return 1;
    }


    //delete
    public int deleteUsername(String username){
        int result = db.delete("User","Username=?",new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }

    //check login
    public int checkLoginStat(String user, String pass){
        db = dbHelper.getReadableDatabase();
        try {
            String check = "SELECT * FROM User WHERE Username='" + user + "' COLLATE NOCASE AND Password='" + pass + "'";
            Cursor cs = db.rawQuery(check, null);
            if (cs.getCount() == 0){
                return -1;
            }
            return 1;
        } finally {
            db.close();
        }
    }

    public int checkUser(String username) {
        db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + "User" + " WHERE Username='" + username + "'";
        Cursor cs = db.rawQuery(sql, null);
        if (cs.getCount() <= 0) {
            return -1;
        }
        cs.close();
        return 1;
    }

    public User getUserByUsername(String username) {
        db = dbHelper.getReadableDatabase();
        User m = null;
        //WHERE clause
        String selection = "username = ?";
        //WHERE clause arguments
        String[] selectionArgs = {username};
        Cursor c = db.query("User",null,selection,selectionArgs,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            m = new User();
            m.setUsername(c.getString(0));
            m.setName(c.getString(1));
            m.setPassword(c.getString(2));
            m.setPhone((c.getString(3)));
            m.setAddr(c.getString(4));
            m.setEmail(c.getString(5));
            break;
        } c.close();
        return m;
    }
}
