package vn.trungkma.money.data.local.db;

import io.realm.DynamicRealm;

public class RealmMessageMigration implements io.realm.RealmMigration {
    public static final long SCHEMA_REALM_VERSION=1;
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        if(oldVersion==1){
            oldVersion++;
        }
        if(oldVersion==2){
            oldVersion++;
        }
        if(oldVersion==3){

        }

    }
}
