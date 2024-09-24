package MyAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.nouroeddinne.eduquest.AddandEditActivity;
import com.nouroeddinne.eduquest.MyViewModel;
import com.nouroeddinne.eduquest.R;

import java.util.List;

import Model.Data;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Data> noteList;
    MyViewModel myViewModel;

    public Adapter(Context context, List<Data> listitems) {
        this.context = context;
        this.noteList = listitems;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(viwe);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Data item = noteList.get(position);
        holder.note.setText(FormatTextNote(item.getNote(),70));
        holder.pos.setText(String.valueOf(position+1));

        myViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MyViewModel.class);



        holder.linear_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(context, AddandEditActivity.class);
//                intent.putExtra("note", item);
//                context.startActivity(intent);
            }
        });

        holder.linear_show.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to Delete Note?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", (dialog, which) ->{
                            myViewModel.deleteNote(item.getId());
                        })
                        .setNegativeButton("No", null)
                        .show();

                return false;
            }
        });
    }

    public int getItemCount() {
        return noteList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView note,pos;
        private LinearLayout linear_show;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.textView_title);
            pos = itemView.findViewById(R.id.textView20);
            linear_show = itemView.findViewById(R.id.linear_show);
        }
    }



    public String FormatTextNote(String s,int n){

        int length = s.length();
        if(length>n){
            s = s.substring(0,n);
            s = s+"...";
            return s;
        }
        return s;
    }




















}
