package fr.leobatouxas.init;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MonGestionnaireBase extends SQLiteOpenHelper {

    private static final String CREATE_PRODUIT = "CREATE TABLE Produit (reference TEXT PRIMARY KEY, designation TEXT NOT NULL, prix FLOAT NOT NULL, Stock FLOAT NOT NULL);";


    public MonGestionnaireBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Produit;");
        onCreate(db);
    }
}
