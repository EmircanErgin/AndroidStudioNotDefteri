package com.example.benimnotum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NotEkle extends AppCompatActivity {

    Toolbar toolbar;
    EditText NotBaslik, NotAyrinti;
    Calendar c;
    String todaysDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Yeni Not");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NotBaslik = findViewById(R.id.NotBaslik);
        NotAyrinti = findViewById(R.id.NotAyrinti);

        NotBaslik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.length() !=0){
                        getSupportActionBar().setTitle(charSequence);
                    }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Geçerli tarihi ve saati al
        c=Calendar.getInstance();
        todaysDate = c.get(Calendar.DAY_OF_MONTH)+"."+(c.get(Calendar.MONTH)+1)+"."+c.get(Calendar.YEAR);
        currentTime = pad (c.get(Calendar.HOUR))+"."+pad (c.get(Calendar.MINUTE));

        Log.d("calendar","Tarih ve Saat: " + todaysDate + " ve " +currentTime);

    }

    private String pad(int i) {
        if(i<10)
           return "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_kayit,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sil){
            Toast.makeText(this,"Not Kaydedilmedi!",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        if(item.getItemId() == R.id.kaydet){
            Not not = new Not(NotBaslik.getText().toString(),NotAyrinti.getText().toString(),todaysDate,currentTime);
            NoteDatabase db = new NoteDatabase(this);
            db.NotEkle(not);
            Toast.makeText(this,"Kaydedildi",Toast.LENGTH_SHORT).show();
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}