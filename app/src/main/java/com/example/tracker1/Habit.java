package com.example.tracker1;

public class Habit {
    private int Id;
    private String Name;
    private int Image;
    private int Status;

    public Habit(int id, String name, int image, int status) // создаем конструктор класса
    {
        this.Id = id;
        this.Name = name;
        this.Image = image;
        this.Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
