package com.project.mobile.dailydraft;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public TaskDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Insert a new task
    public long insertTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TASK, task.getTask());
        values.put(DatabaseHelper.COLUMN_DATE, task.getDate());

        return database.insert(DatabaseHelper.TABLE_TODO, null, values);
    }

    // Get all tasks
    @SuppressLint("Range")
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_TODO,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                task.setTask(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TASK)));
                task.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)));
                tasks.add(task);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return tasks;
    }

}
