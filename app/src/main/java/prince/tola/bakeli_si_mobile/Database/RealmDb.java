package prince.tola.bakeli_si_mobile.Database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import prince.tola.bakeli_si_mobile.Model.Bakeliste;
import prince.tola.bakeli_si_mobile.R;

/**
 * Created by Prince Eros Michel TOLA KOGADOU on 16/09/2019.
 */
public class RealmDb {

    private static RealmDb instance = null;
    private static final String TAG = "RealmDb";
    private Bakeliste bakeliste;

    private Realm realm;
    private Context context;

    public static RealmDb getInstance(Context ctx){
        if (instance == null) instance = new RealmDb(ctx.getApplicationContext());
        return instance;
    }

    public RealmDb(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
//        realm = Realm.getDefaultInstance();
        this.context = context;
    }

    public int getNextKey() {
        bakeliste = new Bakeliste();
        try {
            Number number = realm.where(Bakeliste.class).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public void refresh() {

        realm.refresh();
    }

}
