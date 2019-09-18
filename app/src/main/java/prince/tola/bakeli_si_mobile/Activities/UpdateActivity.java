package prince.tola.bakeli_si_mobile.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import prince.tola.bakeli_si_mobile.Model.Bakeliste;
import prince.tola.bakeli_si_mobile.R;

public class UpdateActivity extends AppCompatActivity {

    EditText edit_prenom;
    EditText edit_nom;
    EditText edit_email;
    Button bt_modifier;
    Realm realm;
    Bakeliste bakeliste;
    String prenom, nom, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit_prenom = findViewById(R.id.edit_prenom);
        edit_nom = findViewById(R.id.edit_nom);
        edit_email = findViewById(R.id.edit_email);
        bt_modifier = findViewById(R.id.bt_modifier);

        prenom = getIntent().getStringExtra("key_prenom");
        nom = getIntent().getStringExtra("key_nom");
        email = getIntent().getStringExtra("key_email");
        edit_prenom.setText(prenom);
        edit_nom.setText(nom);
        edit_email.setText(email);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        bt_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void update(final String id, final String nom) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Bakeliste> bakelistes = realm.where(Bakeliste.class)
                        .equalTo("id", id)
                        .findAll();


            }
        });
        Toast.makeText(UpdateActivity.this, "ça marche", Toast.LENGTH_SHORT).show();
    }
}
