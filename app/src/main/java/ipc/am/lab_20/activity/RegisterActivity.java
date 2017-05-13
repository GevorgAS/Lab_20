package ipc.am.lab_20.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ipc.am.lab_20.R;
import ipc.am.lab_20.db.ProgramDB;
import ipc.am.lab_20.db.entity.User;

public class RegisterActivity extends AppCompatActivity {
    EditText name_et;
    EditText login_et;
    EditText pass_et;
    ProgramDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_et = (EditText) findViewById(R.id.name);
        login_et = (EditText) findViewById(R.id.login_et);
        pass_et = (EditText)findViewById(R.id.password_et);
        db = ProgramDB.getInstance(this);
    }

    public void reg(View view) {
        if (validate()){
            String name = name_et.getText().toString();
            String login = login_et.getText().toString();
            String pass = pass_et.getText().toString();
            User u = new User(name,login,pass);
            db.createUser(u);
        }
        finish();
    }

    private boolean validate(){
        boolean isCorrect = true;
        if (name_et.getText().toString().equals("")){
            name_et.setError("Required");
            isCorrect = false;
        }
        if (login_et.getText().toString().equals("")){
            login_et.setError("Required");
            isCorrect = false;
        }
        if (pass_et.getText().toString().equals("")){
            pass_et.setError("Required");
            isCorrect = false;

        }
        return isCorrect;
    }
}
