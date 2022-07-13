package azoftware.com.whatsappro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class dropActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button,today_button, buttonAll, weekButton, lapsusButton;

    SQLHelper myDB;
    ArrayList<String> IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropactivity);

        recyclerView = findViewById(R.id.lapsusButton2);
        today_button = findViewById(R.id.todayArray);
        add_button = findViewById(R.id.add_button);
        buttonAll = findViewById(R.id.buttonAll);
        weekButton = findViewById(R.id.weekButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                Intent intent = new Intent(dropActivity.this, addRegistroManual.class);
                startActivity(intent);

                }
            }
        );
        today_button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                              Date date = new Date();
                                              String diaActual = dateFormat.format(date);

                                              myDB = new SQLHelper(dropActivity.this);
                                              IDcodigo = new ArrayList<>();
                                              IDdia = new ArrayList<>();
                                              IDEstado = new ArrayList<>();
                                              IDTemperatura = new ArrayList<>();
                                              IDEstadoUv = new ArrayList<>();
                                              IDPresion = new ArrayList<>();
                                              IDhtumedad = new ArrayList<>();

                                              dataInArrayTodayData();
                                              customAdapter = new CustomAdapter(dropActivity.this, IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad);
                                              recyclerView.setAdapter(customAdapter);
                                              recyclerView.setLayoutManager(new LinearLayoutManager(dropActivity.this));


                                          }
                                      }
        );

        buttonAll.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                myDB = new SQLHelper(dropActivity.this);
                                                IDcodigo = new ArrayList<>();
                                                IDdia = new ArrayList<>();
                                                IDEstado = new ArrayList<>();
                                                IDTemperatura = new ArrayList<>();
                                                IDEstadoUv = new ArrayList<>();
                                                IDPresion = new ArrayList<>();
                                                IDhtumedad = new ArrayList<>();

                                                dataInArrayReadAllData();
                                                customAdapter = new CustomAdapter(dropActivity.this, IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad);
                                                recyclerView.setAdapter(customAdapter);
                                                recyclerView.setLayoutManager(new LinearLayoutManager(dropActivity.this));


                                            }
                                        }
        );
        weekButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             myDB = new SQLHelper(dropActivity.this);
                                             IDcodigo = new ArrayList<>();
                                             IDdia = new ArrayList<>();
                                             IDEstado = new ArrayList<>();
                                             IDTemperatura = new ArrayList<>();
                                             IDEstadoUv = new ArrayList<>();
                                             IDPresion = new ArrayList<>();
                                             IDhtumedad = new ArrayList<>();

                                             dataInArrayWeek();
                                             customAdapter = new CustomAdapter(dropActivity.this, IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad);
                                             recyclerView.setAdapter(customAdapter);
                                             recyclerView.setLayoutManager(new LinearLayoutManager(dropActivity.this));


                                         }
                                     }
        );



    }
    public void Lapsus(View view){
        Intent lapsusPantalla = new Intent(dropActivity.this, Lapsus.class);
        startActivity(lapsusPantalla);

    }
    void dataInArrayTodayData(){
        Cursor cursor = myDB.readTodayData();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                IDcodigo.add(cursor.getString(0));
                IDdia.add(cursor.getString(1));
                IDEstado.add(cursor.getString(2));
                IDTemperatura.add(cursor.getString(3));
                IDEstadoUv.add(cursor.getString(4));
                IDPresion.add(cursor.getString(5));
                IDhtumedad.add(cursor.getString(6));

            }
        }
    }


    void dataInArrayReadAllData(){

        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                IDcodigo.add(cursor.getString(0));
                IDdia.add(cursor.getString(1));
                IDEstado.add(cursor.getString(2));
                IDTemperatura.add(cursor.getString(3));
                IDEstadoUv.add(cursor.getString(4));
                IDPresion.add(cursor.getString(5));
                IDhtumedad.add(cursor.getString(6));


            }
        }
    }

    void dataInArrayWeek(){

        Cursor cursor = myDB.readWeekData();
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                IDcodigo.add(cursor.getString(0));
                IDdia.add(cursor.getString(1));
                IDEstado.add(cursor.getString(2));
                IDTemperatura.add(cursor.getString(3));
                IDEstadoUv.add(cursor.getString(4));
                IDPresion.add(cursor.getString(5));
                IDhtumedad.add(cursor.getString(6));

            }
        }
    }
}