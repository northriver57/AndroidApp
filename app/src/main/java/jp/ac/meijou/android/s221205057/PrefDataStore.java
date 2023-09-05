package jp.ac.meijou.android.s221205057;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;



public class PrefDataStore {
    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    private PrefDataStore(RxDataStore<Preferences> dataStore) {
        this.dataStore = dataStore;
    }

    public static PrefDataStore getInstance(Context context) {
        if (instance == null) {


            var dataStore = new RxPreferenceDataStoreBuilder(
                    context.getApplicationContext(), "settings").build();
            instance = new PrefDataStore(dataStore);

        }
        return instance;
    }

}
