package ipc.am.lab_20.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ipc.am.lab_20.R;
import ipc.am.lab_20.db.ProgramDB;

public class MainActivity extends AppCompatActivity {
    EditText login;
    EditText password;
    ProgramDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        db = ProgramDB.getInstance(this);
    }

    public void login(View view) {
        String log = login.getText().toString();
        String pass = password.getText().toString();
        if (log.equals("")&& pass.equals("")){
            login.setError("Login wrong");
            password.setError("or pass wrong");
        }else if (db.validateUser(log,pass)){
            Intent i = new Intent(this,NoteActivity.class);
            int id = db.findUserByID(log,pass);
            String name = db.findUser(log,pass);
            i.putExtra("u_name",name);
            i.putExtra("u_id",id);
            startActivity(i);
        }else {
            login.setError("Login wrong");
            password.setError("or pass wrong");
        }

    }

    public void register(View view) {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
}
