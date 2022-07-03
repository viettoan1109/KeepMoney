package vn.trungkma.money;

import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;
import vn.trungkma.money.common.Constant;
import vn.trungkma.money.data.local.db.RealmMessageMigration;
import vn.trungkma.money.utils.LocaleUtils;

@HiltAndroidApp
public class App extends MultiDexApplication {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.setErrorHandler(Timber::w);
        initLog();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Constant.DATABASE_NAME)
                .migration(new RealmMessageMigration())
                .compactOnLaunch()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static App getInstance() {
        return instance;
    }

    private void initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        LocaleUtils.applyLocale(newBase);
//        super.attachBaseContext(newBase);
//    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.applyLocale(this);
    }
}
