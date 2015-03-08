package com.example.colloquium3;


public class Task {
    public int id_subject = 0;
    public String name = "";
    public int points = 0;

    public Task(String name)
    {
        this.name = name;
    }

    public Task(int id_subject)
    {
        this.id_subject = id_subject;
    }
}
