package com.example.colloquium3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.Vector;

public class TasksActivity extends Activity {
    public SubjectsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_activity);

        Database.init(this);

        Vector<Task> e = new Vector<>();

        adapter = new SubjectsAdapter(this, e, this);
        ListView list_view = (ListView) findViewById(R.id.subjectsListView);
        list_view.setAdapter(adapter);
    }

    public void gotoAddSubject(View v) {
        Intent intent = new Intent(this, SubjectEditActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.channels.clear();
        Cursor sth = Database.gi().query("select tasks.id_task, tasks.name from tasks");
        while (sth.moveToNext()) {
            Console.print(sth.getString(0) + " " + sth.getString(1));
            Task task = new Task(sth.getString(1));
            task.id_subject = Integer.parseInt(sth.getString(0));
            task.points = 0;
            adapter.channels.add(task);
        }
        adapter.notifyDataSetChanged();
    }
}
