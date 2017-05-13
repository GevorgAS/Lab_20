package ipc.am.lab_20.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import ipc.am.lab_20.R;
import ipc.am.lab_20.db.ProgramDB;
import ipc.am.lab_20.db.entity.Note;
import ipc.am.lab_20.db.entity.User;

public class AddNoteActivity extends AppCompatActivity {
    EditText text_et;
    EditText time_et;
    EditText date_et;
    ProgramDB db;
    int u_id;
    Intent i;

//DatePickerDialog variables
    int year;
    int month;
    int day;
//TimePickerDialog variables
    int minute;
    int hour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        text_et = (EditText) findViewById(R.id.text_et);
        time_et = (EditText) findViewById(R.id.time_et);
        date_et = (EditText) findViewById(R.id.date_et);
        db = ProgramDB.getInstance(this);
        i = getIntent();
        u_id = i.getIntExtra("u_id",0);
    }

    public void save(View view) {
        String text = text_et.getText().toString();
        String time = time_et.getText().toString();
        String date = date_et.getText().toString();
        long id = (long) u_id;
        User u = new User(id);
        Note note = new Note(text,time,date,u);
        db.createNote(note);
        i.putExtra("id",id);
        setResult(200,i);
        finish();
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            String mm = (month+1)<10?"0" + (month+1):""+(month+1);
            date_et.setText(new StringBuilder().append(day)
                    .append("/").append(mm).append("/").append(year)
                    .append(" "));

        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int selectedMinute) {
            hour = hourOfDay;
            minute = selectedMinute;

            time_et.setText(new StringBuilder().append(hour).append(":").append(minute).append(" "));
        }
    };


    public void dateSet(View view) {
        new DatePickerDialog(this, datePickerListener,year, month,day).show();
    }

    public void timeSet(View view) {
        new TimePickerDialog(this,timePickerListener,hour,minute,true).show();
    }
}
