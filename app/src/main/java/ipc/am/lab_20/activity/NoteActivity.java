package ipc.am.lab_20.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ipc.am.lab_20.R;
import ipc.am.lab_20.db.ProgramDB;
import ipc.am.lab_20.db.entity.Note;
import ipc.am.lab_20.db.entity.User;

public class NoteActivity extends AppCompatActivity {
    EditText search_et;
    TextView name_et;
    ListView lv;
    int u_id;
    List<Note> notes;
    ArrayAdapter<Note> adapter;
    ProgramDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        search_et = (EditText) findViewById(R.id.search_et);
        name_et = (TextView) findViewById(R.id.username_tv);
        lv = (ListView) findViewById(R.id.lv);

        Intent i = getIntent();
        name_et.setText(i.getStringExtra("u_name"));
        u_id = i.getIntExtra("u_id",1);
        notes = new ArrayList<>();

        db = ProgramDB.getInstance(this);
        User u = new User(u_id);
        notes = db.getNotesByUser(u);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void search(View view) {
        String search = search_et.getText().toString();
        notes = db.searchNotes(search,u_id);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void add_note(View view) {
        Intent intent = new Intent(this,AddNoteActivity.class);
        intent.putExtra("u_id",u_id);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100 && resultCode ==200){
            long id = data.getLongExtra("id",0);
            User user = new User(id);
            notes = db.getNotesByUser(user);
            adapter = new ArrayAdapter<Note>(this,android.R.layout.simple_list_item_1,notes);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    
                }
            });
        }

    }
}
