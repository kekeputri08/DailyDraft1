package com.project.mobile.dailydraft;

public class Task {

        private long id;

        public String getTask() {
                return task;
        }

        public void setTask(String task) {
                this.task = task;
        }

        private String task;

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        private String date;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        // Constructor, getter, and setter methods
        // ...


        @Override
        public String toString() {
                return "" + task + "\n" +
                        " Date : " + date;
        }


}
