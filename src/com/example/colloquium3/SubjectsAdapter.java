package com.example.colloquium3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Vector;

public class SubjectsAdapter extends ArrayAdapter<Task> {
    public Vector<Task> channels;
    private TasksActivity program;

    public SubjectsAdapter(Context context, Vector<Task> tasks, TasksActivity program) {
        super(context, R.layout.task_layout, tasks);
        this.channels = tasks;
        this.program = program;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        final Task task = channels.get(index);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View entryView = inflater.inflate(R.layout.task_layout, parent, false);

        TextView subjectNameView = (TextView) entryView.findViewById(R.id.subjectName);
        subjectNameView.setText(task.name);

        TextView subtasksCountView = (TextView) entryView.findViewById(R.id.subtasksCount);
        subtasksCountView.setText(task.points + "");

        entryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(program, SubTasksActivity.class);
                intent.putExtra("ID_SUBJECT", task.id_subject);
                program.startActivity(intent);
            }
        });

        entryView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(program, SubjectEditActivity.class);
                intent.putExtra("ID_SUBJECT", task.id_subject);
                program.startActivity(intent);
                return true;
            }
        });

        return entryView;
    }
}
