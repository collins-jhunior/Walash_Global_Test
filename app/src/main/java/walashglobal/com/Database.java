package walashglobal.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DN = "data.db";
    private SQLiteDatabase wdb = getWritableDatabase();

    Database(Context context) {
        super(context, DN, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IDS (ID TEXT)");
        db.execSQL("create table data_info (ID TEXT, DATE TEXT, AMOUNT_CONTRIBUTED TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists IDS");
        db.execSQL("drop table if exists data_info");
        onCreate(db);
    }

    Long insert_into_data_info(String id, String date, String amount) {
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("DATE", date);
        cv.put("AMOUNT_CONTRIBUTED", amount);
        return wdb.insert("data_info", null, cv);
    }

    void insert_id(String id) {
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        wdb.insert("IDS", null, cv);
    }

    Cursor id_check(String id) {
        return wdb.rawQuery("SELECT * FROM IDS where id = '" + id + "'", null);
    }

    Cursor data_info_check() {
        return wdb.rawQuery("SELECT * FROM data_info", null);
    }
}
