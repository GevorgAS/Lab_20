package ipc.am.lab_20.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ipc.am.lab_20.db.ProgramHelper;
import ipc.am.lab_20.db.entity.Note;
import ipc.am.lab_20.db.entity.User;
import ipc.am.lab_20.db.repository.functions.Note_I;
import ipc.am.lab_20.db.table.NoteTable;
import ipc.am.lab_20.db.table.UserTable;

public class NoteDB implements Note_I {
    private SQLiteDatabase database;
    private ProgramHelper dbHelper;


    public NoteDB(Context context){
        dbHelper = new ProgramHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }


    @Override
    public void createNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.COLUMN_TEXT,note.getText());
        values.put(NoteTable.COLUMN_TIME,note.getTime());
        values.put(NoteTable.COLUMN_DATE,note.getDate());
        values.put(NoteTable.COLUMN_USER_ID,note.getUser().getId());
        database.insert(NoteTable.TABLE_NAME,null,values);
    }

    @Override
    public List<Note> getNotesByUser(User u) {
        List<Note> notes = new ArrayList<>();
        String query = "select * from " + NoteTable.TABLE_NAME + " where u_id = ?";

        Cursor cursor = database.rawQuery(query,new String[]{u.getId()+""});

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }

    @Override
    public List<Note> searchNotes(String search,long u_id) {
        List<Note> notes = new ArrayList<>();
        String query = "select * from " + NoteTable.TABLE_NAME + " where "+NoteTable.COLUMN_TEXT+" like ? " +
                "and "+NoteTable.COLUMN_USER_ID+" like ?";
                ;
        Cursor cursor = database.rawQuery(query, new String[]{"%" + search + "%",u_id+""});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(0));
        note.setText(cursor.getString(1));
        note.setTime(cursor.getString(2));
        note.setDate(cursor.getString(3));

        long u_id  = cursor.getInt(4);
        String query = "select * from " + UserTable.TABLE_NAME+" where id=?";
        Cursor c = database.rawQuery(query,new String[]{u_id+""});
        c.moveToFirst();
        User u = null;
        if (!c.isAfterLast()){
            u = new User();
            u.setId(c.getLong(0));
        }
        c.close();
        note.setUser(u);
        return note;
    }
}
