package fr.leobatouxas.init;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AffichageProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_produit);
        ArrayList<CharSequence> mesProduits = new ArrayList<CharSequence>();
        ArrayList<CharSequence> mesProduitsDes = new ArrayList<CharSequence>();
        Cursor c = Global.bdd.rawQuery("SELECT reference,designation, prix, stock FROM Produit", null);
        while (c.moveToNext( ))
        {
            // ** Traitement de l'enregistrement lu. Par ex, le numéro isbn lu se trouve dans : c.getString(0)
            // ** (0 = numéro de colonne)
            mesProduits.add(c.getString(1));
            mesProduitsDes.add(c.getString(0));
        }

        final Spinner maListeDeroulante;
        maListeDeroulante = (Spinner)findViewById(R.id.ListProduit);

        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence> (this, android.R.layout.simple_list_item_1, mesProduits);
        maListeDeroulante.setAdapter(DataAdapter);

        final Spinner ListProduit = (Spinner) findViewById(R.id.ListProduit);
        Button afficher;
        afficher = (Button) findViewById(R.id.ButtonAfficherProduit);
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Spinner ListProduit = (Spinner) findViewById(R.id.ListProduit);
                ArrayList<CharSequence> produit = new ArrayList<CharSequence>();
                Cursor co = Global.bdd.rawQuery("SELECT reference,designation, prix, stock FROM Produit WHERE Produit.reference = ?", new String[] {mesProduitsDes.get(ListProduit.getSelectedItemPosition()).toString()});
                while (co.moveToNext( ))
                {
                    // ** Traitement de l'enregistrement lu. Par ex, le numéro isbn lu se trouve dans : c.getString(0)
                    // ** (0 = numéro de colonne)
                    produit.add(co.getString(0));
                    produit.add(co.getString(1));
                    produit.add(co.getString(2));
                    produit.add(co.getString(3));
                }

                AlertDialog.Builder adb = new AlertDialog.Builder(AffichageProduit.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle(produit.get(1));
                //on insère un message à notre boite de dialogue
                adb.setMessage("reference: " + produit.get(0) + "\nprix: " + produit.get(2) + "\nstock: " + produit.get(3));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                // on affiche la boite de dialogue
                adb.show();
            };
        });



        // Liste View
        ArrayList<CharSequence> mesProduitsl = new ArrayList<CharSequence>();
        Cursor cu = Global.bdd.rawQuery("SELECT reference,designation FROM Produit", null);
        while (cu.moveToNext( ))
        {
            // ** Traitement de l'enregistrement lu. Par ex, le numéro isbn lu se trouve dans : c.getString(0)
            // ** (0 = numéro de colonne)
            mesProduitsl.add(cu.getString(1));
        }

        final ListView ListView;
        ListView = (ListView)findViewById(R.id.listViewProduit);

        ArrayAdapter<CharSequence> DataAdapter2 = new ArrayAdapter<CharSequence> (this, android.R.layout.simple_list_item_1, mesProduitsl);
        ListView.setAdapter(DataAdapter2);



    }
}