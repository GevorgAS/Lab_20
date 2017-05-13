package ipc.am.lab_20.db.repository.functions;

import java.util.List;

import ipc.am.lab_20.db.entity.Note;
import ipc.am.lab_20.db.entity.User;

/**
 * Created by Gevorg on 5/8/2017.
 */

public interface Note_I {
    void createNote(Note note);
    List<Note> getNotesByUser(User u);
    List<Note> searchNotes(String search,long u_id);
}
