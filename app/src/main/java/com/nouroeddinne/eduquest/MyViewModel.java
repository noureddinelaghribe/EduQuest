package com.nouroeddinne.eduquest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Database.Reposetry;
import Model.Data;

public class MyViewModel extends AndroidViewModel {

    Reposetry reposetry;
    public MyViewModel(@NonNull Application application) {
        super(application);
        reposetry = new Reposetry(application);
    }


    public void insertNote(Data note){
        reposetry.insertNote(note);
    }

//    public LiveData<List<Data>> getAllNotes(){
//        return reposetry.getAllNotes();
//    }
    public LiveData<List<Data>> getAllNotesByName(){
        return reposetry.getAllNotesByName();
    }


    public LiveData<List<Data>> get5NotesByName(int p){
        return reposetry.get5NotesByName(p);
    }

    public LiveData<Data> getNoteById(int p){
        return reposetry.getNoteById(p);
    }


    public int getCount() {
        return reposetry.getCount();
    }

//    public LiveData<List<Data>> searchNotes(String text){
//        return reposetry.searchNotes(text);
//    }

    public void updateNote(Data note){
        reposetry.updateNote(note);
    }

//    public LiveData<List<Data>> getAllSaveNote(){
//        return reposetry.getAllSaveNote();
//    }
//    public LiveData<List<Data>> getAllSaveNoteByName(){
//        return reposetry.getAllSaveNoteByName();
//    }

    public void deleteNote(int id){
        reposetry.deleteNote(id);

    }


}
