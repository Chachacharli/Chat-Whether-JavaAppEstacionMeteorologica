package azoftware.com.whatsappro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyviewHolder> {
    private Context context;
    private ArrayList rId, rDia, rEstado, rtemperatura, rEstadoUv, rPresion, rHumedad;
    CustomAdapter(Context context,
                  ArrayList rId, ArrayList rDia, ArrayList rEstado,
                  ArrayList rtemperatura,ArrayList  rEstadoUv,
                  ArrayList rPresion,ArrayList rHumedad){

        this.context = context;
        this.rId = rId;
        this.rDia = rDia;
        this.rEstado = rEstado;
        this.rEstadoUv = rEstadoUv;
        this.rHumedad = rHumedad;
        this.rPresion = rPresion;
        this.rtemperatura = rtemperatura;

    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.one_row, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        try {
            holder.idRegistro.setText(String.valueOf(rId.get(position)));
        }catch (Exception e){
        }
        try{
            holder.txtDia.setText(String.valueOf(rDia.get(position)));
        }catch (Exception e){}
        try {
            holder.txtEstado.setText(String.valueOf(rEstado.get(position)));

        }catch (Exception e){}
        try {
            holder.txtEstadoUv.setText(String.valueOf(rEstadoUv.get(position)));

        }catch (Exception e){}
        try {
            holder.txtTemperatura.setText(String.valueOf(rtemperatura.get(position)));
        }catch (Exception e){}
        try{
            holder.txtPresion.setText(String.valueOf(rPresion.get(position)));

        }catch (Exception e){}
        try {
            holder.txtHumedad.setText(String.valueOf(rHumedad.get(position)));
        }catch (Exception e){}



    }

    @Override
    public int getItemCount() {
        return rId.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        TextView idRegistro, txtDia,txtEstado, txtTemperatura, txtEstadoUv, txtHumedad, txtPresion;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            idRegistro =itemView.findViewById(R.id.idRegistro);
            txtDia =itemView.findViewById(R.id.txtDia);
            txtEstado =itemView.findViewById(R.id.txtEstado);
            txtTemperatura =itemView.findViewById(R.id.txtTemperatura);
            txtEstadoUv =itemView.findViewById(R.id.txtEstadoUV);
            txtHumedad =itemView.findViewById(R.id.txtHumedad);
            txtPresion =itemView.findViewById(R.id.txtPresion);

        }
    }

    }

