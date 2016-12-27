
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cookie on 2016/12/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "content_data.db";
    private static final String TABLE_NAME_CONTENT = "content_table";
    private static final String TABLE_NAME_IN = "in_table";
    private static SQLiteDatabase mSqLiteDatabase = null;

    private DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (null == mSqLiteDatabase) {
            synchronized (DataWork.class) {
                if (null == mSqLiteDatabase) {
                    mSqLiteDatabase = new DataBaseHelper(context
                            , DATABASE_NAME
                            , null
                            , DATABASE_VERSION)
                            .getWritableDatabase();
                }
            }
        }
        return mSqLiteDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("create table if not exists "
                    + TABLE_NAME_IN
                    + "(password varchar(6))");
            db.execSQL("create table if not exists "
                    + TABLE_NAME_CONTENT
                    + "(type int,title varchar(255),descrip varchar(255),password varchar(255))");
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Tag", "database error:" + e);
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
