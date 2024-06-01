package nicolas.example.manualparapracticantes;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.List;
import java.util.zip.Inflater;

import nicolas.example.manualparapracticantes.extras.ListElement;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context){
        this.mInflater = LayoutInflater.from(mInflater.getContext());
        this.context = context;
        this.mData = itemList;
    }


    @Override
    public int getItemCount(){
        return 4;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }


    public  void setItems(List<ListElement> items){
        mData = items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView Nivel, descripcion;
        ViewHolder(View itemView){
         super(itemView);
         iconImage = itemView.findViewById(R.id.IconImage);
         Nivel = itemView.findViewById(R.id.nameTextView);
         descripcion = itemView.findViewById(R.id.Descripcion);
        }
        void bindData(final ListElement item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            Nivel.setText(item.getNivel());
            descripcion.setText(item.getDescripcion());
        }
    }

}
