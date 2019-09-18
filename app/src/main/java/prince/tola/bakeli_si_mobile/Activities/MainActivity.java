package prince.tola.bakeli_si_mobile.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import prince.tola.bakeli_si_mobile.Database.RealmDb;
import prince.tola.bakeli_si_mobile.Model.Bakeliste;
import prince.tola.bakeli_si_mobile.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Spinner spinner, spinner2;
    TextInputEditText ed_prenom, ed_nom, ed_email, ed_phone, ed_adresse;
    String prenom, nom, email, telephone, adresse;
    String id;
    Realm realm;
    private RealmDb realmDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        button = (Button) findViewById(R.id.button);

        ed_prenom = (TextInputEditText) findViewById(R.id.ed_prenom);
        ed_nom = (TextInputEditText) findViewById(R.id.ed_nom);
        ed_email = (TextInputEditText) findViewById(R.id.ed_email);
        ed_phone = (TextInputEditText) findViewById(R.id.ed_phone);
        ed_adresse = (TextInputEditText) findViewById(R.id.ed_adresse);

       /* realmDb = new RealmDb(getApplicationContext());
        realmDb = RealmDb.getInstance(getApplicationContext());
*/
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.civilite, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.matrimoniale, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prenom = ed_prenom.getText().toString();
                nom = ed_nom.getText().toString();
                email = ed_email.getText().toString();
                telephone = ed_phone.getText().toString();
                //telephoneInt = Integer.parseInt(telephone);
                adresse = ed_adresse.getText().toString();

                ajouter();
                Toast.makeText(MainActivity.this, "Information Ajouter" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ListeBakeli.class);
                startActivity(intent);
            }
        };
        button.setOnClickListener(onClickListener);
    }


    public void ajouter(){
        realm.beginTransaction();

        id = UUID.randomUUID().toString();
        Bakeliste bakeliste = realm.createObject(Bakeliste.class, UUID.randomUUID().toString());
       // bakeliste.setId(realmDb.getNextKey());
        bakeliste.setPrenom(prenom);
        bakeliste.setNom(nom);
        bakeliste.setEmail(email);
        bakeliste.setTelephone(telephone);
        bakeliste.setAdresse(adresse);

        realm.commitTransaction();
    }

    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }
}
 