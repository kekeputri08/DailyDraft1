package com.project.mobile.dailydraft;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;


public class TodolistActivity extends AppCompatActivity {
    private TaskDataSource dataSource;
    private EditText editTask, editDate;
    private Button btnAddTask;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Drafts");

        dataSource = new TaskDataSource(this);
        dataSource.open();

        editTask = findViewById(R.id.editTask);
        editDate = findViewById(R.id.editDate);
        btnAddTask = findViewById(R.id.btnAddTask);
        listView = findViewById(R.id.listView);


        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
                displayTasks();
            }
        });

        displayTasks();
    }


    private void addTask() {
        String taskText = editTask.getText().toString().trim();
        String dateText = editDate.getText().toString().trim();

        if (!taskText.isEmpty() && !dateText.isEmpty()) {
            Task task = new Task();
            task.setTask(taskText);
            task.setDate(dateText);

            dataSource.insertTask(task);

            // Clear input fields after adding task
            editTask.getText().clear();
            editDate.getText().clear();
        }
    }

    private void displayTasks() {
        List<Task> tasks = dataSource.getAllTasks();
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}