package ipc.am.lab_20.db.table;

/**
 * Created by Gevorg on 5/8/2017.
 */

public class UserTable {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "u_name";
    public static final String COLUMN_LOGIN = "u_login";
    public static final String COLUMN_PASSWORD = "u_password";


    public static final String CREATE_TABLE_USER = "create table "+TABLE_NAME+" ( " +
            COLUMN_ID+" integer primary key autoincrement ," +
            COLUMN_NAME+" text , " + COLUMN_LOGIN+" text , " +COLUMN_PASSWORD+" text );";

    public static final String[] allColumns(){
        return new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_LOGIN,COLUMN_PASSWORD};
    }


}
