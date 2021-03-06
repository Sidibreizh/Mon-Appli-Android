package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddContactActivity extends AppCompatActivity {

    private ImageView imageView;
    private RadioGroup radioGroup;
    private RadioButton radioButtonM;
    private RadioButton radioButtonF;
    private Button boutonValidation, dateBouton;
    private EditText nom, prenom, phone, mail, address;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initDatePicker();

        this.imageView = this.findViewById(R.id.imageView);

        radioGroup = findViewById(R.id.radioGroup);
        radioButtonF = findViewById(R.id.radioButtonF);
        radioButtonM = findViewById(R.id.radioButtonM);
        boutonValidation = findViewById(R.id.boutonValidation);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        dateBouton = findViewById(R.id.date_picker);
        phone = findViewById(R.id.phone);
        mail = findViewById(R.id.mail);
        address = findViewById(R.id.address);
        dateBouton.setText(getTodaysDate());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id)
            {
                View radioButton = radioGroup.findViewById(id);
                int index = radioGroup.indexOfChild(radioButton);

                if(index == 0)
                {
                    showImage1();
                }
                else
                {
                    showImage2();
                }
            }
        });

        boutonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!mail.getText().toString().equals(""))
                {
                    if (!isValidEmail(mail.getText().toString())) {
                        Toast.makeText(AddContactActivity.this, "Votre email n'est pas correctement rempli", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!nom.getText().toString().equals("") && !prenom.getText().toString().equals("") && !phone.getText().toString().equals("")){

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity.this);
                    builder.setMessage(nom.getText().toString()+ "\n"+ prenom.getText().toString()+ "\n" + phone.getText().toString());
                    builder.setTitle("Vos donn??es ont ??t?? ajout??es ?? la liste de contacts :");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    Intent i = new Intent(getApplication(),ContactListe.class);
                    i.putExtra("nom",nom.getText().toString());
                    i.putExtra("prenom",prenom.getText().toString());
                    i.putExtra("telephone",phone.getText().toString());
                    startActivity(i);
                }

                else{
                    Toast toast = Toast.makeText(AddContactActivity.this,"Vous devez remplir les diff??rents champs obligatoires", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private String getTodaysDate() {
        Calendar calendrier = Calendar.getInstance();
        int jour = calendrier.get(Calendar.DAY_OF_MONTH);
        int mois = calendrier.get(Calendar.MONTH);
        mois = mois + 1;
        int an = calendrier.get(Calendar.YEAR);
        return makeDateString(jour,mois,an);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int day, int month, int year)
                {
                    month = month + 1;
                    String date = makeDateString(day, month, year);
                    dateBouton.setText(date);
            }
        };

        Calendar calendrier = Calendar.getInstance();
        int jour = calendrier.get(Calendar.DAY_OF_MONTH);
        int mois = calendrier.get(Calendar.MONTH);
        int an = calendrier.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this, dateSetListener,an,mois,jour);

    }

    private String makeDateString(int day, int month, int year) {

        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEV";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "AVR";
        if(month == 5)
            return "MAI";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AOU";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }

    public  void openDatePicker(View view){
        datePickerDialog.show();
    }
    private void showImage1()
    {
        this.imageView.setImageResource(R.drawable.photofunky);
    }
    private void showImage2()
    {
        this.imageView.setImageResource(R.drawable.femme_image_animee_0029);
    }
    public static boolean isValidEmail(CharSequence cible) {
        return (!TextUtils.isEmpty(cible) && Patterns.EMAIL_ADDRESS.matcher(cible).matches());
    }

}
