package lab.pam.pwr.lab1_pam;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.ReyclerViewHolder>  {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Photo> items;
    private int height;
    private int width;
    private int position;

    public Adapter(Context context, ArrayList<Photo> items, int height, int width) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
        this.width = width;
        this.height = height;

    }

    @NonNull
    @Override
    public ReyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, int position) {
        Photo item = items.get(holder.getAdapterPosition());
        //this.position = holder.getAdapterPosition();
        //TODO proba glide, jak cos to nizej dziala
        // dziala dla malych obrazow  holder.image.setImageResource(item.getResourceId());
        Glide.with(context).load(item.getResourceId()).into(holder.image);

        //Glide.with(context).load(item).into(holder.image);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        private ReyclerViewHolder(final View v) {
            super(v);
            image = v.findViewById(R.id.image);
            int orientation = context.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                image.getLayoutParams().height = height % 3 == 0 ? height / 3 : height % 3 == 1 ? height / 3 + 2 : height / 3 + 1;
                image.getLayoutParams().width =  width% 3 == 0 ? width/ 3 : width% 3 == 1 ? width/ 3 + 2 : width / 3 + 1;
                image.requestLayout();
            } else {
                image.getLayoutParams().height = height / 4;
                image.getLayoutParams().width = width / 2;
                image.requestLayout();
            }

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoActivity.start(context, getItemViewType());
                }
            });

        }
    }
}
