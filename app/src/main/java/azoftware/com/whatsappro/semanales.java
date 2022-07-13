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

import java.util.ArrayList;

public class semanales extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    SQLHelper myDB;
    ArrayList<String> IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semanales);

        recyclerView = findViewById(R.id.lapsusButton2);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent(semanales.this, addRegistroManual.class);
                                              startActivity(intent);

                                          }
                                      }
        );
        myDB = new SQLHelper(semanales.this);
        IDcodigo = new ArrayList<>();
        IDdia = new ArrayList<>();
        IDEstado = new ArrayList<>();
        IDTemperatura = new ArrayList<>();
        IDEstadoUv = new ArrayList<>();
        IDPresion = new ArrayList<>();
        IDhtumedad = new ArrayList<>();

        dataInArray();
        customAdapter = new CustomAdapter(semanales.this, IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(semanales.this));
    }
    void dataInArray(){

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