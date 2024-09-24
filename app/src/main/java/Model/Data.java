package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import Utles.Utel;

@Entity(tableName = Utel.TABLE_NAME)
public class Data {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Utel.KEY_ID)
    private int id;
    @ColumnInfo(name = Utel.KEY_NOTE)
    private String note;
    @ColumnInfo(name = Utel.KEY_REPLAY)
    private int replay;

    public Data(int id, String note, int replay) {
        this.id = id;
        this.note = note;
        this.replay = replay;
    }

    public Data(String note, int replay) {
        this.note = note;
        this.replay = replay;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReplay() {
        return replay;
    }

    public void setReplay(int replay) {
        this.replay = replay;
    }
}
