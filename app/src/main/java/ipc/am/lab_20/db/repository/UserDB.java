package ipc.am.lab_20.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ipc.am.lab_20.db.ProgramHelper;
import ipc.am.lab_20.db.entity.User;
import ipc.am.lab_20.db.repository.functions.User_I;
import ipc.am.lab_20.db.table.UserTable;

/**
 * Created by Gevorg on 5/8/2017.
 */

public class UserDB implements User_I {
    private SQLiteDatabase database;
    private ProgramHelper dbHelper;


    public UserDB(Context context){
        dbHelper = new ProgramHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    @Override
    public void createUser(User u) {
        ContentValues values = new ContentValues();
        values.put(UserTable.COLUMN_NAME,u.getName());
        values.put(UserTable.COLUMN_LOGIN,u.getLogin());
        values.put(UserTable.COLUMN_PASSWORD,u.getPassword());
        database.insert(UserTable.TABLE_NAME,null,values);
    }

    @Override
    public boolean validateUser(String login, String pass) {
        boolean isCorrect = false;
        String query = "select * from "+UserTable.TABLE_NAME + " where ( " + UserTable.COLUMN_LOGIN +" like ? and "+
                UserTable.COLUMN_PASSWORD+" like ? )";

        String[] params = {"%"+login+"%","%"+pass+"%"};
        Cursor cursor = database.rawQuery(query,params);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(!cursor.isNull(2)){
                isCorrect = true;
                break;
            }
            cursor.moveToNext();

        }
        cursor.close();
        return isCorrect;
    }

    @Override
    public String findUser(String login, String pass) {
        String name = null;
        String query = "select * from "+UserTable.TABLE_NAME + " where ( " + UserTable.COLUMN_LOGIN +" like ? and "+
                UserTable.COLUMN_PASSWORD+" like ? )";

        String[] params = {"%"+login+"%","%"+pass+"%"};
        Cursor cursor = database.rawQuery(query,params);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(!cursor.isNull(1)){
//                u = cursorToUser(cursor);
//                name = u.getName();
                name = cursor.getString(1);
                break;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return name;
    }

    @Override
    public int findUserByID(String login, String pass) {
       int id=0;

        String query = "select * from "+UserTable.TABLE_NAME + " where ( " + UserTable.COLUMN_LOGIN +" like ? and "+
                UserTable.COLUMN_PASSWORD+" like ? )";

        String[] params = {"%"+login+"%","%"+pass+"%"};
        Cursor cursor = database.rawQuery(query,params);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(!cursor.isNull(1)){
                id = cursor.getInt(0);
                break;
            }
            cursor.moveToNext();
        }
        cursor.close();

        return id;
    }

    private User cursorToUser(Cursor c){
        User u = new User();
        u.setName(c.getString(1));
        return u;
    }
}
