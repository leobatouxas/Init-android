package fr.leobatouxas.init;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hello extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        Intent thisIntent = getIntent();
        String login = thisIntent.getExtras().getString("login");
        String pass = thisIntent.getExtras().getString("password");
        TextView loginText = (TextView) findViewById(R.id.text15);
        String message = "Hello, " + login;
        loginText.setText(message);

        ArrayList<CharSequence> mesMetiers = new ArrayList<CharSequence>();
        mesMetiers.add("bucheron");
        mesMetiers.add("plombier");
        final Spinner maListeDeroulante;
        maListeDeroulante = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence> (this, android.R.layout.simple_spinner_item, mesMetiers);
        maListeDeroulante.setAdapter(DataAdapter);

        Button valider;
        valider = (Button) findViewById(R.id.ButtonAfficherProduit);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on crée une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(Hello.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle("Item sélectionné");
                //on insère un message à notre boite de dialogue
                adb.setMessage(maListeDeroulante.getItemAtPosition(maListeDeroulante.getSelectedItemPosition()).toString());
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                // on affiche la boite de dialogue
                adb.show();
            };
        });
    }
}