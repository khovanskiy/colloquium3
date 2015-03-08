package com.example.colloquium3;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SubjectEditActivity extends Activity {
    private int id_subject = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_activity);

        id_subject = getIntent().getIntExtra("ID_SUBJECT", 0);

        if (id_subject != 0) {
            Cursor c = Database.gi().query("select * from tasks where id_task = " + id_subject);
            c.moveToNext();
            EditText name = (EditText) findViewById(R.id.editSubjectName);
            name.setText(c.getString(1));
            ((Button) findViewById(R.id.deleteButton)).setEnabled(true);
        } else {
            ((Button) findViewById(R.id.deleteButton)).setEnabled(false);
        }
    }

    public void onSaveButtonClicked(View v) {
        EditText name = (EditText) findViewById(R.id.editSubjectName);

        Cursor c = Database.gi().query("select * from tasks where id_task = " + id_subject);
        if (c.getCount() == 0) {
            Database.gi().exec("insert into tasks values(null,'" + (name.getText().toString()) + "')");
            Toast t = Toast.makeText(this, "New task is added", Toast.LENGTH_SHORT);
            t.show();
            c.close();
            finish();
        } else {
            Database.gi().exec("update tasks set name = '" + (name.getText().toString()) + "' where id_task = " + id_subject);
            Toast t = Toast.makeText(this, "Task is updated", Toast.LENGTH_SHORT);
            t.show();
            c.close();
        }
    }

    public void onDeleteButtonClicked(View v) {
        Database.gi().exec("delete from tasks where id_task = " + id_subject);
        Database.gi().exec("delete from subtasks where id_task = " + id_subject);
        Toast t = Toast.makeText(this, "Task is deleted", Toast.LENGTH_SHORT);
        t.show();
        finish();
    }
}
