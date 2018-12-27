package gewalin.th.ac.rmutl.physical;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyManage {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
    }

    public long addValueToSQLite(String genderString,
                                 String ageString,
                                 String praralysistypeString,
                                 String musclepower,
                                 String detail) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Gender", genderString);
        contentValues.put("Age", ageString);
        contentValues.put("PraralysisType", praralysistypeString);
        contentValues.put("MusclePower", musclepower);
        contentValues.put("Detail", detail);

        return sqLiteDatabase.insert("userTABLE", null, contentValues);

    }


}
