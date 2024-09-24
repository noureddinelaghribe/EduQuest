package Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Data;
import Utles.Utel;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Data note);

    //@Query("SELECT * FROM "+ Utel.TABLE_NAME +" ORDER BY "+Utel.KEY_DATE+" DESC")
    //LiveData<List<Note>> getAllNotes();
    @Query("SELECT * FROM "+ Utel.TABLE_NAME)
    LiveData<List<Data>> getAllNotesByName();

    @Query("SELECT * FROM "+ Utel.TABLE_NAME+" LIMIT 5 OFFSET :p")
    LiveData<List<Data>> get5NotesByName(int p);

    @Query("SELECT * FROM "+ Utel.TABLE_NAME +" WHERE "+Utel.KEY_ID+" = :p")
//    @Query("SELECT * FROM "+ Utel.TABLE_NAME +" WHERE "+Utel.KEY_ID+" = 0 AND  :p ORDER BY "+ Utel.KEY_ID +" ASC LIMIT 1")
    LiveData<Data> getNoteById(int p);

    @Query("SELECT COUNT(*) FROM "+Utel.TABLE_NAME)
    int getCount();
    
    //@Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE " + Utel.KEY_NOTE + " LIKE '%' || :text || '%' OR " + Utel.KEY_TITLE + " LIKE '%' || :text || '%'")
    //LiveData<List<Note>> searchNotes(String text);

    @Update
    void updateNote(Data note);

    //@Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE "+Utel.KEY_FAVORITE+"=1 ORDER BY "+Utel.KEY_DATE+" DESC")
    //LiveData<List<Note>> getAllSaveNote();

    //@Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE "+Utel.KEY_FAVORITE+"=1 ORDER BY "+Utel.KEY_TITLE+" ASC")
    //LiveData<List<Note>> getAllSaveNoteByName();

    @Query("DELETE FROM " + Utel.TABLE_NAME + " WHERE " + Utel.KEY_ID + " = :id")
    void deleteNote(int id);













































}
