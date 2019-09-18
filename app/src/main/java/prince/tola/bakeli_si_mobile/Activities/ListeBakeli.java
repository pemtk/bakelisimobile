package prince.tola.bakeli_si_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import prince.tola.bakeli_si_mobile.Adapter.BakeliAdapter;
import prince.tola.bakeli_si_mobile.Model.Bakeliste;
import prince.tola.bakeli_si_mobile.R;

public class ListeBakeli extends AppCompatActivity {
    private static final String TAG = "ListeBakeli";

    RecyclerView recyclerView;
    List<Bakeliste> prenom = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();
    BakeliAdapter adapter;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_bakeli);
        Log.d(TAG, "onCreate: started.");
        realm = Realm.getDefaultInstance();

        afficher();

    }

    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = findViewById(R.id.rv);
        BakeliAdapter adapter = new BakeliAdapter(this, prenom);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void afficher(){
        Log.d(TAG, "initImageBitmap: preparing bitmap.");
        RealmResults<Bakeliste> results = realm.where(Bakeliste.class).findAll();

        for(Bakeliste bakeliste : results){

            Bakeliste Prenom = new Bakeliste(bakeliste.getPrenom(), bakeliste.getNom(), bakeliste.getEmail(), bakeliste.getAdresse(), bakeliste.getTelephone());
            prenom.add(Prenom);
        }
        initRecyclerView();
    }
}
