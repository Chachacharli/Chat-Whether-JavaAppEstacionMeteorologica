package azoftware.com.whatsappro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class brachMeteorologico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brach_meteorologico);
    }

    public void irAMetero(View view){
        Intent intentChat = new Intent(brachMeteorologico.this, dropActivity.class);
        startActivity(intentChat);

    }

   public void irAChat(View view){
       Intent intentChat = new Intent(brachMeteorologico.this, InicioActivity.class);
       startActivity(intentChat);

   }

}