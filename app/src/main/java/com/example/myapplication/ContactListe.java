package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ContactListe extends AppCompatActivity{

    ListView ls;
    String nom, telephone;
    HashMap<String,String> mapContact;
    static Contacts c = new Contacts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_liste);

        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom") + " " + extras.getString("prenom");
        telephone = "\t\t" + extras.getString("telephone");

        ls = findViewById(R.id.liste);
        mapContact = new HashMap<>();
        mapContact.put("nom",nom);
        mapContact.put("prenom",nom);
        mapContact.put("telephone",telephone);
        c.values.add(mapContact);

        SimpleAdapter adapter = new SimpleAdapter(ContactListe.this, c.values,R.layout.contact_item, new String[]{"nom","telephone"},
                new int[]{R.id.tvNom,R.id.tvTel});
    ls.setAdapter(adapter);
    }
}