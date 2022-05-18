package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;

public class ContactListe extends AppCompatActivity implements RecyclerViewInterface{

    ListView ls;
    String nom, telephone;
    HashMap<String,String> mapContact;
    Contacts c = new Contacts();
    SimpleAdapter adapter;
    RecyclerViewInterface recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_liste);

        Bundle extras = getIntent().getExtras();
        nom = extras.getString("nom") + " " + extras.getString("prenom");
        telephone = "\t\t" + extras.getString("telephone");

        ls = findViewById(R.id.liste);
        mapContact = new HashMap<String,String>();
        mapContact.put("nom",nom);
        mapContact.put("prenom",nom);
        mapContact.put("telephone",telephone);
        c.values.add(mapContact);

        adapter = new SimpleAdapter(ContactListe.this, c.values,R.layout.contact_item, new String[]{"nom","telephone"},
                new int[]{R.id.tvNom,R.id.tvTel});
    ls.setAdapter(adapter);

    ls.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            int position = ls.getPositionForView(v);
            recycler.onItemLongClick(position);
            return true;
        }
    });

    }

    @Override
    public void onItemLongClick(int position) {
        c.values.remove(position);
        adapter.notifyDataSetChanged();
    }
}