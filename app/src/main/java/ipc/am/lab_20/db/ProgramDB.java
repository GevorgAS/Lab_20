package ipc.am.lab_20.db;

import android.content.Context;

import java.util.List;

import ipc.am.lab_20.db.entity.Note;
import ipc.am.lab_20.db.entity.User;
import ipc.am.lab_20.db.repository.NoteDB;
import ipc.am.lab_20.db.repository.UserDB;
import ipc.am.lab_20.db.repository.functions.Note_I;
import ipc.am.lab_20.db.repository.functions.User_I;


public class ProgramDB implements User_I,Note_I {
    private UserDB u_db;
    private NoteDB n_db;
    private static ProgramDB db;

    private ProgramDB(Context context){
        u_db = new UserDB(context);
        n_db = new NoteDB(context);
    }
    public static ProgramDB getInstance(Context context){
        if(db == null){
            db = new ProgramDB(context);
        }
        return db;
    }


    @Override
    public void createUser(User u) {
        u_db.open();
        u_db.createUser(u);
        u_db.close();

    }

    @Override
    public boolean validateUser(String login, String pass) {
        u_db.open();
        boolean b =  u_db.validateUser(login, pass);
        u_db.close();
        return b;
    }

    @Override
    public String findUser(String login, String pass) {
        u_db.open();
        String name = u_db.findUser(login,pass);
        u_db.close();
        return name;
    }

    @Override
    public int findUserByID(String login, String pass) {
        u_db.open();
        int id = u_db.findUserByID(login,pass);
        u_db.close();
        return id;
    }

    @Override
    public void createNote(Note note) {
        n_db.open();
        n_db.createNote(note);
        n_db.close();
    }

    @Override
    public List<Note> getNotesByUser(User u) {
        n_db.open();
        List<Note> notes = n_db.getNotesByUser(u);
        n_db.close();
        return notes;
    }

    @Override
    public List<Note> searchNotes(String search,long u_id) {
        n_db.open();
        List<Note> notes = n_db.searchNotes(search,u_id);
        n_db.close();
        return notes;
    }
}
