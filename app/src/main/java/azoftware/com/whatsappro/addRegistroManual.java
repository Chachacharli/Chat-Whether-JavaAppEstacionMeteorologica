package azoftware.com.whatsappro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addRegistroManual extends AppCompatActivity {
    private EditText etCodigo, etDia, etEstado, etTemperatura, etEstadoUv, etPresion, etHumedad;
    Button addButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_registro_manual);

        //Datos
        int temperatura = 0;
        int estadoUV = 0;
        int presion = 0;
        int humedad = 0;


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String dia = dateFormat.format(date);

        temperatura = (int) (Math.random()*(38-36+1)+36);
        estadoUV = (int) (Math.random()*(.99-.90+1)+.90);
        presion = (int) (Math.random()*(958-940+1)+940);
        humedad = (int) (Math.random()*(41-38+1)+38);


        etDia = findViewById(R.id.etDia);

        etEstado = (EditText) findViewById(R.id.etEstado);
        etTemperatura = (EditText) findViewById(R.id.etTemperatura);
        etEstadoUv = (EditText) findViewById(R.id.etEstadoUv);
        etPresion = (EditText) findViewById(R.id.etPresion);
        etHumedad = (EditText) findViewById(R.id.etHumedad);
        addButton = (Button) findViewById(R.id.addButton);

        String finalTemperatura = String.valueOf(temperatura);
        String finalEstadoUv = String.valueOf(estadoUV);
        String finalPresion = String.valueOf(presion);
        String finalHumedad = String.valueOf(humedad);
        String estado = "Soleado";
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 SQLHelper Database = new  SQLHelper(addRegistroManual.this);
                Database.addRegistro(
                        dia,
                        estado,
                        finalTemperatura ,
                        finalEstadoUv,
                        finalPresion,
                        finalHumedad+ "%"
                );

                Toast.makeText(addRegistroManual.this, "Temperatura " + finalPresion, Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void Siguiente(View view){
        Intent siguiente = new Intent(this,  semanales.class);
        startActivity(siguiente);
    }
}