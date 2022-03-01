package fr.leobatouxas.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SaisieProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_produit);


        Button saisie = (Button) findViewById(R.id.buttonValid);
        saisie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText reference = (EditText) findViewById(R.id.editTextReference);
                String referenceStr = reference.getText().toString();
                EditText designation = (EditText) findViewById(R.id.editTextDesignation);
                String designationStr = designation.getText().toString();
                EditText prix = (EditText) findViewById(R.id.editTextPrix);
                String prixStr = prix.getText().toString();
                EditText stock = (EditText) findViewById(R.id.editTextStock);
                String stockStr = stock.getText().toString();
                ContentValues values = new ContentValues();
                values.put("reference", referenceStr);
                values.put("designation", designationStr);
                values.put("prix", prixStr);
                values.put("stock", stockStr);

                ArrayList<CharSequence> mesProduits = new ArrayList<CharSequence>();
                Cursor cu = Global.bdd.rawQuery("SELECT reference FROM Produit", null);
                while (cu.moveToNext( ))
                {
                    // ** Traitement de l'enregistrement lu. Par ex, le numéro isbn lu se trouve dans : c.getString(0)
                    // ** (0 = numéro de colonne)
                    mesProduits.add(cu.getString(0));
                }
                if(mesProduits.contains(referenceStr))
                {
                    Toast.makeText(SaisieProduit.this, "La référence du produit existe déjà", Toast.LENGTH_LONG).show();
                }
                else {
                    Global.bdd.insert("produit", null, values);
                    Toast.makeText(SaisieProduit.this, "Le produit " + designationStr + " à éttait crée", Toast.LENGTH_LONG).show();
                }






            }
        });
    }
}