package ipc.am.lab_20.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ipc.am.lab_20.db.table.NoteTable;
import ipc.am.lab_20.db.table.UserTable;

/**
 * Created by Gevorg on 5/8/2017.
 */

public class ProgramHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "program.db";
    private static final int DATABASE_VERSION = 1;

    public ProgramHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.CREATE_TABLE_USER);
        db.execSQL(NoteTable.CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NoteTable.TABLE_NAME);
        onCreate(db);
    }
}
