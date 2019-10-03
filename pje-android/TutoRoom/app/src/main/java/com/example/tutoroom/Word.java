package com.example.tutoroom;

import androidx.room.Entity;

import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String word;

    public Word(String word) {this.word = word;}

    public String getWord(){return this.word;}

    public  int getId(){
        return this.id;
    }
    public void setId(int i){
        this.id = i;
    }
}