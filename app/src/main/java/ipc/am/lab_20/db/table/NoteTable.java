package ipc.am.lab_20.db.table;

/**
 * Created by Gevorg on 5/8/2017.
 */

public class NoteTable {
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "n_text";
    public static final String COLUMN_TIME = "n_time";
    public static final String COLUMN_DATE = "n_date";
    public static final String COLUMN_USER_ID = "u_id";

    public static final String CREATE_TABLE_NOTE = "create table "+TABLE_NAME+" ( " +
            COLUMN_ID+" integer primary key autoincrement ," +
            COLUMN_TEXT+" text , "+COLUMN_TIME+" integer , " +
            COLUMN_DATE+" integer , " +COLUMN_USER_ID+" integer );";

    public static final String[] allColumns(){
        return new String[]{COLUMN_ID,COLUMN_TEXT,COLUMN_TIME,COLUMN_DATE,COLUMN_USER_ID};
    }

}
