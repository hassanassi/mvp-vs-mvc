package project.demo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import project.demo.R;
import project.demo.model.Item;
import project.demo.view.util.UnitConverter;


/**
 * Created by hassan on 8/31/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    public interface ClickListener {
        void onItemClick(View view, int position);
    }
    //just as an example
    private int dpSize= (int) UnitConverter.dpToPx(100);

    ArrayList<Item> itemList;
    ClickListener clickListener;

    public Adapter(ArrayList<Item> newsList, ClickListener clickListener){
        this.itemList=newsList;
        this.clickListener=clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //build view from xml
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(itemList.get(position).getTitle());


        //load image from url using picasso library
        Picasso.with(holder.imageView.getContext())
                .load(itemList.get(position).getImageUrl())
        .placeholder(R.drawable.placeholder).resize(dpSize,dpSize).centerInside().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textView= (TextView) itemView.findViewById(R.id.title);
            this.imageView=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
