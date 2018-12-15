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

        sqlProd = "Insert Into Product values ( 'SM021','Samsung Galaxy Note 9','17.000.000 Đ', 'Điện thoại flagship đến từ Samsung đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Samsung ra mắt', 'https://i.imgur.com/gru5CzF.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SO32','Sony XZ2','15.000.000 Đ', 'Điện thoại flagship đến từ Sony đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Sony ra mắt', 'https://i.imgur.com/WItCuxV.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'XO24','Xiaomi Mi Mix 3','19.000.000 Đ', 'Điện thoại flagship đến từ Xiaomi đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Xiaomi ra mắt', 'https://i.imgur.com/gje3Qrp.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'LG22','LG V30','8.000.000 Đ', 'Điện thoại flagship đến từ LG đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của LG ra mắt', 'https://i.imgur.com/u8OuZXh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'XO43','Xiaomi Mi 8','17.000.000 Đ', 'Điện thoại flagship đến từ Xiaomi đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Xiaomi ra mắt', 'https://i.imgur.com/hlvPY0L.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'LG21','LG G7 ThinQ','10.000.000 Đ', 'Điện thoại flagship đến từ LG đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của LG ra mắt', 'https://i.imgur.com/bQ1ETEm.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'IP8P','IPhone 8 Plus','19.000.000 Đ', 'Điện thoại flagship đến từ Apple đã nhanh chóng vượt mặt LG trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Apple ra mắt', 'https://i.imgur.com/poL5xMi.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SV1P','VSmart Active 1+','6.500.000 Đ', 'Điện thoại flagship đến từ VSmart đã nhanh chóng vượt mặt Xiaomo trong cuộc đua phân khúc trung cấp', 'Flagship hàng đầu của VSmart ra mắt', 'https://i.imgur.com/d2UD2xi.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'HW8','Huawei Honor 8','10.000.000 Đ', 'Điện thoại flagship đến từ Huawei đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Huawei ra mắt', 'https://i.imgur.com/XsBveYA.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'IPXS','IPhone Xs Max','24.000.000 Đ', 'Điện thoại flagship đến từ Apple đã nhanh chóng vượt mặt Sasung trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Apple ra mắt', 'https://i.imgur.com/KtfZl9w.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'HW20','Huawei Mate 20 Pro','24.000.000 Đ', 'Điện thoại flagship đến từ Huawei đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Huawei ra mắt', 'https://i.imgur.com/0vR2t75.jpg')";
        db.execSQL(sqlProd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
