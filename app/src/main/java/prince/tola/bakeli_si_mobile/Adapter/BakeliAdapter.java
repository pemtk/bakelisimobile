package prince.tola.bakeli_si_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.realm.Realm;
import prince.tola.bakeli_si_mobile.Activities.UpdateActivity;
import prince.tola.bakeli_si_mobile.Model.Bakeliste;
import prince.tola.bakeli_si_mobile.R;

/**
 * Created by Prince Eros Michel TOLA KOGADOU on 11/09/2019.
 */
public class BakeliAdapter extends RecyclerView.Adapter<BakeliAdapter.ViewHolder> {

    private static final String TAG = "BakeliAdapter";

    //private ArrayList<String> mImages= new ArrayList<>();
    private List<Bakeliste> bakelisteList;
    private Context mContext;
    Realm realm;
    private int previousPosition = 0;

    public BakeliAdapter(Context mContext,List<Bakeliste> bakelisteList) {
        this.bakelisteList = bakelisteList;
        //mImages = images;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        realm = Realm.getDefaultInstance();
        Log.d(TAG, "onBindViewHolder: called.");
        final int currentPosition = position;
        final Bakeliste bakeliste = bakelisteList.get(position);

        /*Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);*/

        holder.prenom.setText(bakelisteList.get(position).getPrenom());
        holder.nom.setText(bakelisteList.get(position).getNom());

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: "+ bakelisteList.get(position).getPrenom());

                Toast.makeText(mContext, bakelisteList.get(position).getPrenom(), Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.bt_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(bakeliste);
                Toast.makeText(mContext, "Suppréssion réussi", Toast.LENGTH_SHORT).show();

            }
        });



        holder.bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gPrenom = bakelisteList.get(position).getPrenom();
                String gNom = bakelisteList.get(position).getNom();
                String gEmail = bakelisteList.get(position).getEmail();

                Intent intent = new Intent(mContext, UpdateActivity.class);
                intent.putExtra("key_prenom", gPrenom);
                intent.putExtra("key_nom", gNom);
                intent.putExtra("key_email", gEmail);

                mContext.startActivity(intent);
        }
        });
    }

    private void removeItem(Bakeliste bakeliste) {

        int currPosition = bakelisteList.indexOf(bakeliste);
        bakelisteList.remove(currPosition);
        notifyItemRemoved(currPosition);
    }



    /**
     * Méthode qui indique le nombre de ligne à créer
     * @return
     */
    @Override
    public int getItemCount() {
        return bakelisteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //CircleImageView image;
        TextView prenom;
        TextView nom;
        TextView email;
        Button bt_supp, bt_edit;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            prenom = (TextView) itemView.findViewById(R.id.tv_prenom);
            nom = (TextView) itemView.findViewById(R.id.tv_nom);
            email = (TextView) itemView.findViewById(R.id.tv_email);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            bt_supp = itemView.findViewById(R.id.bt_supp);
            bt_edit = itemView.findViewById(R.id.bt_edit);
        }
    }

}
