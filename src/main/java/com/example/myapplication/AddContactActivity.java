package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;


import java.util.ArrayList;

public class AddContactActivity extends AppCompatActivity {

    RadioButton radioButtonM;
    RadioButton radioButtonF;
    Button boutonValidation;
    EditText nom, prenom, birthDate, phone, mail, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        radioButtonF = findViewById(R.id.radioButtonF);
        radioButtonM = findViewById(R.id.radioButtonM);
        boutonValidation = findViewById(R.id.boutonValidation);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        birthDate = findViewById(R.id.birthDate);
        phone = findViewById(R.id.phone);
        mail = findViewById(R.id.mail);
        address = findViewById(R.id.address);

        boutonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nom.getText().toString().equals("") && !prenom.getText().toString().equals("") && !phone.getText().toString().equals("")){

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity.this);
                    builder.setMessage(nom.getText().toString()+ "\n"+ prenom.getText().toString()+ "\n" + phone.getText().toString());
                    builder.setTitle("Vos données ont été ajoutées à la liste de contacts :");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    Intent i = new Intent(getApplication(),ContactListe.class);
                    i.putExtra("nom",nom.getText().toString());
                    i.putExtra("prenom",prenom.getText().toString());
                    i.putExtra("telephone",phone.getText().toString());
                    startActivity(i);
                }

                else{
                    Toast toast = Toast.makeText(AddContactActivity.this,"Vous devez remplir les différents champs obligatoires", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}