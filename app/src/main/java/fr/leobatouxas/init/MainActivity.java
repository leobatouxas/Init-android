package fr.leobatouxas.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.leobatouxas.init.ui.login.LoginActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** Création d'une instance de la classe MonGestionnaireBase */
        int VERSION_BDD = 1;
        MonGestionnaireBase monGestionnaireBase = new MonGestionnaireBase(getBaseContext( ),"produits.db",null, VERSION_BDD);
        /*** Ouverture de la BDD en écriture, avec création si elle n'existe pas
         /** On appelle la méthode getWritableDatabase( ) de la classe MonGestionnaireBase, qui en fait :
         /** - appelle sa méthode onCreate (dans laquelle a été mise l'instruction de la création de la base,
         dans le cas où la base n'existe pas.
         /** appelle sa méthode onUpgrade, dans le cas où la base a changé de version (le numéro de version a
         /** été passé au constructeur de la classe MonGestionnaireBase, avec la variable VERSION_BDD)
         */
        Global.bdd = monGestionnaireBase.getWritableDatabase();

        Button calculer;
        calculer = (Button) findViewById(R.id.ButtonValider);
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText FraisTaxi;
                EditText FraisTrain;
                EditText FraisSaisie;
                double total;
                FraisTaxi = (EditText) findViewById(R.id.editTextTextFraisTaxi);
                FraisTrain = (EditText) findViewById(R.id.editTextTextFraisTrain);
                FraisSaisie = (EditText) findViewById(R.id.editTextTextFraisSaisie);
                total = Double.parseDouble(FraisTaxi.getText().toString());
                total = total + Double.parseDouble(FraisTrain.getText().toString());
                FraisSaisie =  (EditText) findViewById(R.id.editTextTextFraisSaisie);
                FraisSaisie.setText(String.valueOf(total));
            }
        });
        Button saisieproduit = (Button) findViewById(R.id.buttonSaisieProduit);
        saisieproduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, SaisieProduit.class);
                startActivity(intent);
            }
        });


        Button loginButton;
        loginButton = (Button) findViewById(R.id.ButtonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button AffichageProduitButton;
        AffichageProduitButton = (Button) findViewById(R.id.AffichageProduitButton);
        AffichageProduitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, AffichageProduit.class);
                startActivity(intent);
            }
        });
    }

}