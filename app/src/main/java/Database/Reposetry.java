package Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import Model.Data;

public class Reposetry {

    NoteDao noteDao;

    public Reposetry(Application application) {
        Database database = Database.getDatabase(application);
        noteDao = database.noteDao();
    }

    public Reposetry(Database db) {
        this.noteDao = db.noteDao();
    }


    public void insertNote(Data note){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insertNote(note);
            }
        });
    }

//    public LiveData<List<Note>> getAllNotes(){
//        return noteDao.getAllNotes();
//    }

    public LiveData<List<Data>> getAllNotesByName(){
        return noteDao.getAllNotesByName();
    }

    public LiveData<List<Data>> get5NotesByName(int p){
        return noteDao.get5NotesByName(p);
    }

    public LiveData<Data> getNoteById(int p){
        return noteDao.getNoteById(p);
    }

    public int getCount() {
        return noteDao.getCount();
    }

//    public LiveData<List<Note>> searchNotes(String text){
//        return noteDao.searchNotes(text);
//    }

    public void updateNote(Data note){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.updateNote(note);
            }
        });
    }

//    public LiveData<List<Note>> getAllSaveNote(){
//        return noteDao.getAllSaveNote();
//    }
//    public LiveData<List<Note>> getAllSaveNoteByName(){
//        return noteDao.getAllSaveNoteByName();
//    }

    public void deleteNote(int id){
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteNote(id);
            }
        });
    }








































}
