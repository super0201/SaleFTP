package database;

import android.content.Context;
import android.database.Cursor;
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
        String sqlProd = "CREATE TABLE Product(ID text primary key," + "Name text, Price double, Detail text, Summary text, Image text)";
        db.execSQL(sqlProd);

        String sqlDetail = "CREATE TABLE Detail(ID text primary key," + "Screen text, ScrRes, FrCam text, ReaCam text, CPU text, Ram text, Rom text, Sim text, MCard text, BattCapa text, OS text)";
        db.execSQL(sqlDetail);

        // insert basic info to Product
        sqlProd = "Insert Into Product values ( 'SM021','Samsung Galaxy Note 9', 17000000 , 'Điện thoại flagship đến từ Samsung đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Samsung ra mắt', 'https://i.imgur.com/gru5CzFh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SO32','Sony XZ2', 15000000 , 'Điện thoại flagship đến từ Sony đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Sony ra mắt', 'https://i.imgur.com/WItCuxVh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'XO24','Xiaomi Mi Mix 3', 19000000, 'Điện thoại flagship đến từ Xiaomi đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Xiaomi ra mắt', 'https://i.imgur.com/gje3Qrph.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'LG22','LG V30', 8000000, 'Điện thoại flagship đến từ LG đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của LG ra mắt', 'https://i.imgur.com/u8OuZXhh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'XO43','Xiaomi Mi 8', 17000000, 'Điện thoại flagship đến từ Xiaomi đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Xiaomi ra mắt', 'https://i.imgur.com/hlvPY0Lh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'LG21','LG G7 ThinQ', 10000000, 'Điện thoại flagship đến từ LG đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của LG ra mắt', 'https://i.imgur.com/bQ1ETEmh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'IP8P','IPhone 8 Plus', 19000000, 'Điện thoại flagship đến từ Apple đã nhanh chóng vượt mặt LG trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Apple ra mắt', 'https://i.imgur.com/poL5xMih.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'SV1P','VSmart Active 1+', 6500000, 'Điện thoại flagship đến từ VSmart đã nhanh chóng vượt mặt Xiaomo trong cuộc đua phân khúc trung cấp', 'Flagship hàng đầu của VSmart ra mắt', 'https://i.imgur.com/d2UD2xih.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'HW8','Huawei Honor 8', 10000000, 'Điện thoại flagship đến từ Huawei đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Huawei ra mắt', 'https://i.imgur.com/XsBveYAh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'IPXS','IPhone Xs Max', 24000000, 'Điện thoại flagship đến từ Apple đã nhanh chóng vượt mặt Sasung trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Apple ra mắt', 'https://i.imgur.com/KtfZl9wh.jpg')";
        db.execSQL(sqlProd);

        sqlProd = "Insert Into Product values ( 'HW20','Huawei Mate 20 Pro', 24000000, 'Điện thoại flagship đến từ Huawei đã nhanh chóng vượt mặt Apple trong cuộc đua phân khúc cao cấp', 'Flagship hàng đầu của Huawei ra mắt', 'https://i.imgur.com/0vR2t75h.jpg')";
        db.execSQL(sqlProd);

        //insert detail Product
        sqlDetail = "Insert Into Detail values ( 'SM021', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh, có sạc nhanh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'SO32', 'IPS HDR LCD, 5.7\", Full HD+', '1080 x 2160 Pixels', '5 MP', '19 MP', 'Snapdragon 845 8 nhân', '4 GB', '64 GB', '2 Nano SIM, Hỗ trợ 4G', 'MicroSD, hỗ trợ tối đa 400 GB', '3180 mAh, có sạc nhanh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'XO24', 'Super AMOLED, 6.39\", Full HD+', '1080 x 2340 Pixels', '24 MP và 2 MP Dual Camera', 'Dual Camera 12 MP', 'Snapdragon 845 8 nhân', '10 GB', '512 GB', '2 Nano SIM, Hỗ trợ 4G', 'MicroSD, hỗ trợ tối đa 256 GB', '3200 mAh, có sạc nhanh', 'Android 9.0 (Pie)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'LG22', 'P-OLED, 6.0\", Quad HD (2K)', '1440 x 2560 pixels (~474 dpi)', '8 MP', 'Dual Camera 16MP + 13 MP', 'Qualcomm MSM8998 Snapdragon 835', '4 GB', '64 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 256 GB', '4000 mAh', 'Android 7.1 (Nougat)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'XO43', 'Super AMOLED, 6.21\", Full HD+', '1080 x 2248 Pixels', '20 MP', 'Dual Camera 12 MP', 'Snapdragon 845 8 nhân', '6 GB', '64 GB', '2 Nano SIM, Hỗ trợ 4G', 'MicroSD, hỗ trợ tối đa 512 GB', '3400 mAh, có sạc nhanh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'LG21', 'IPS LCD, 6.1\", Quad HD+ (2K+)', '1440 x 2880 Pixels', '5 MP', 'Dual Camera 16 MP', 'Snapdragon 845 8 nhân', '6 GB', '128 GB', '2 Nano SIM, Hỗ trợ 4G', 'MicroSD, hỗ trợ tối đa 400 GB', '3000 mAh, có sạc nhanh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'IP8P', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'SV1P', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'HW8', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'IPXS', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);

        sqlDetail = "Insert Into Detail values ( 'HW20', 'Super AMOLED, 6.4\", Quad HD+ (2K+)', '1440 x 2960 pixels', '8 MP', 'Dual Camera 12 MP', 'Exynos 9810 8 nhân 64 bit', '6 GB', '128 GB', '1 Sim (Nano Sim)', 'MicroSD, hỗ trợ tối đa 512 GB', '4000 mAh', 'Android 8.0 (Oreo)')";
        db.execSQL(sqlDetail);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
