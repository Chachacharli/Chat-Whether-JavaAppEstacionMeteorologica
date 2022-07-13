package azoftware.com.whatsappro;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Lapsus extends AppCompatActivity {
    int qEstado =0,  qTemperatura=0,  qPresion =0,  qEstadoUV=0,  qHumedad=0;
    Button buttonFechaFin, buttonFechaInicio;
    EditText etFechaInicio, etFechaFin;
    TextView tvFechaInicio, tvFechaFin;
    FloatingActionButton mandarDatos;
    SQLHelper myDB;
    ArrayList<String> IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    Switch sEstado, sTemperatura, sPresion, sEstadoUV, sHumedad;
    public String fechaInicio, fechaFin;


    private int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapsus);


        buttonFechaInicio = (Button) findViewById(R.id.buttonFechaInicio);
        buttonFechaFin = (Button) findViewById(R.id.buttonFechaFin);
        etFechaInicio = (EditText) findViewById(R.id.txtFechaInicio);
        etFechaFin = (EditText) findViewById(R.id.txtFechaFin);
        tvFechaInicio  = (TextView) findViewById(R.id.tvFechaIni);
        tvFechaFin  = (TextView) findViewById(R.id.tvFechaIni);
        mandarDatos = (FloatingActionButton) findViewById(R.id.mandarDatos);
        recyclerView = findViewById(R.id.lapsusButton2);

        //Switches
        sEstado = (Switch) findViewById(R.id.sEstado);
        sTemperatura = (Switch) findViewById(R.id.sTemperatura);
        sPresion = (Switch) findViewById(R.id.sPresion);
        sEstadoUV = (Switch) findViewById(R.id.sEstadoUV);
        sHumedad = (Switch) findViewById(R.id.sHumedad);

        // SWITCHES PARA FILTROS
        sEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.sEstado){
                    if(sEstado.isChecked()){
                        Toast.makeText(Lapsus.this,"SE ACTIVO ESTADO", Toast.LENGTH_SHORT).show();
                        qEstado = 1;
                    }else{
                        Toast.makeText(Lapsus.this,"SE DESACTIVO ESTADO", Toast.LENGTH_SHORT).show();
                        qEstado = 0;
                    }
                }
            }
        });
        sTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.sTemperatura){
                    if(sTemperatura.isChecked()){
                        Toast.makeText(Lapsus.this,"SE ACTIVO TEMPERATURA", Toast.LENGTH_SHORT).show();
                        qTemperatura= 1;
                    }else{
                        Toast.makeText(Lapsus.this,"SE DESACTIVO TEMPERATURA", Toast.LENGTH_SHORT).show();
                        qTemperatura = 0;
                    }
                }
            }
        });
        sPresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.sPresion){
                    if(sPresion.isChecked()){
                        Toast.makeText(Lapsus.this,"SE ACTIVO PRESION", Toast.LENGTH_SHORT).show();
                        qPresion = 1;
                    }else{
                        Toast.makeText(Lapsus.this,"SE DESACTIVO PRESION", Toast.LENGTH_SHORT).show();
                        qPresion = 0;
                    }
                }
            }
        });
        sHumedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.sHumedad){
                    if(sHumedad.isChecked()){
                        Toast.makeText(Lapsus.this,"SE ACTIVO HUMEDAD", Toast.LENGTH_SHORT).show();
                        qHumedad = 1;
                    }else{
                        Toast.makeText(Lapsus.this,"SE DESACTIVO HUMEDAD", Toast.LENGTH_SHORT).show();
                        qHumedad = 0;
                    }
                }
            }
        });
        sEstadoUV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.sEstadoUV){
                    if(sEstadoUV.isChecked()){
                        Toast.makeText(Lapsus.this,"SE ACTIVO UV", Toast.LENGTH_SHORT).show();
                        qEstadoUV = 1;
                    }else{
                        Toast.makeText(Lapsus.this,"SE DESACTIVO UV", Toast.LENGTH_SHORT).show();
                        qEstadoUV = 0;
                    }
                }
            }
        });

        // BOTON PARA MANDAR A LLAMAR QUERY
        mandarDatos.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               myDB = new SQLHelper(Lapsus.this);
                                               IDcodigo = new ArrayList<>();
                                               IDdia = new ArrayList<>();
                                               IDEstado = new ArrayList<>();
                                               IDTemperatura = new ArrayList<>();
                                               IDEstadoUv = new ArrayList<>();
                                               IDPresion = new ArrayList<>();
                                               IDhtumedad = new ArrayList<>();

                                               dataInLapsus();
                                               customAdapter = new CustomAdapter(Lapsus.this, IDcodigo, IDdia, IDEstado, IDTemperatura, IDEstadoUv, IDPresion, IDhtumedad);
                                               recyclerView.setAdapter(customAdapter);
                                               recyclerView.setLayoutManager(new LinearLayoutManager(Lapsus.this));


                                           }
                                       }
        );

    }

    public void Mesfin(View v){

            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog dpDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    if(day>9) {
                        fechaFin = year + "/0" + (month + 1) + "/" + day;
                        Toast.makeText(Lapsus.this, fechaFin, Toast.LENGTH_SHORT).show();
                        etFechaFin.setText(fechaFin);
                    }else{
                        fechaFin = year + "/0" + (month + 1) + "/0" + day;
                        Toast.makeText(Lapsus.this, fechaFin, Toast.LENGTH_SHORT).show();
                        etFechaFin.setText(fechaFin);
                    }
                }
            },anio,mes,dia);
            dpDialog.show();


    }
    public void MesInin(View v){

        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog dpDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                if(day>9) {
                    fechaInicio = year + "/0" + (month + 1) + "/" + day;
                    Toast.makeText(Lapsus.this, fechaInicio, Toast.LENGTH_SHORT).show();
                    etFechaInicio.setText(fechaInicio);
                }else{
                    fechaInicio = year + "/0" + (month + 1) + "/0" + day;
                    Toast.makeText(Lapsus.this, fechaInicio, Toast.LENGTH_SHORT).show();
                    etFechaInicio.setText(fechaInicio);
                }
            }
        },anio,mes,dia);
        dpDialog.show();


    }
    void dataInLapsus(){
        Cursor cursor = myDB.readLapsoData(fechaInicio, fechaFin, qEstado,  qTemperatura,  qPresion ,  qEstadoUV,  qHumedad);
        if(cursor.getCount() ==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                IDcodigo.add(cursor.getString(0));
                IDdia.add(cursor.getString(1));
                if (qEstado == 1){
                    IDEstado.add(cursor.getString(2));
                }
                if (qTemperatura == 1){
                    IDTemperatura.add(cursor.getString(3));
                }
                if (qEstadoUV == 1){
                    IDEstadoUv.add(cursor.getString(4));

                }
                if (qPresion == 1 ){
                    IDPresion.add(cursor.getString(5));

                }
                if (qHumedad == 1){
                    IDhtumedad.add(cursor.getString(6));
                }

            }
        }
    }
}