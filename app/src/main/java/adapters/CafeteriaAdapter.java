package adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.cardslib.R;

import java.util.ArrayList;
import java.util.List;

import models.Cafeteria;

/**
 * Created by Nico on 11/12/2016.
 */

public class CafeteriaAdapter extends RecyclerView.Adapter<CafeteriaAdapter.MyViewHolder> {

    private ArrayList<Cafeteria> CafeteriasList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name, phone, email;
        private ImageView image;

        private MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.cafeteriaName);
            email = (TextView) view.findViewById(R.id.cafeteriaEmail);
            phone = (TextView) view.findViewById(R.id.cafeteriaPhone);
            image = (ImageView) view.findViewById(R.id.cafeteriaImage);
        }

    }


    public CafeteriaAdapter(ArrayList<Cafeteria> CafeteriasList,Context context) {
        this.CafeteriasList = CafeteriasList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cafeteria_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Cafeteria Cafeteria = CafeteriasList.get(position);
        holder.name.setText(Cafeteria.getName());
        holder.email.setText("Email: " + Cafeteria.getEmail());
        holder.phone.setText("Tel: " + Cafeteria.getPhone());

        Glide.with(context).load(Cafeteria.getImage()).into(holder.image);
    }



    @Override
    public int getItemCount() {
        return CafeteriasList.size();
    }
}
