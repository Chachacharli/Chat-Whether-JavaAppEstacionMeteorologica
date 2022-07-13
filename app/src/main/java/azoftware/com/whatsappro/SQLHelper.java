package azoftware.com.whatsappro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SQLHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "Administracion";
    public static final int  DATABAE_VERSION = 4;

    public static final String TABLE_NAME = "REGISTROS";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DIA = "dia";
    public static final String COLUMN_ESTADO = "estado";
    public static final String COLUMN_TEMPERATURA = "temperatura";
    public static final String COLUMN_ESTADOUV = "estadoUv";
    public static final String COLUMN_PRESION = "presion";
    public static final String COLUMN_HUMEDAD = "humedad";



    public SQLHelper(Context context){
        super(context, DATABASE_NAME, null, DATABAE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos3) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DIA + " DATA, " + COLUMN_ESTADO + " TEXT, " +
                COLUMN_TEMPERATURA + " TEXT, " + COLUMN_ESTADOUV + " TEXT, " +
                COLUMN_PRESION + " TEXT, " + COLUMN_HUMEDAD + " TEXT);";
        BaseDeDatos3.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos3, int i, int i1) {
        BaseDeDatos3.execSQL("DROP TABLE IF EXISTS  " + TABLE_NAME);
        onCreate(BaseDeDatos3);

    }
    void addRegistro(String dia, String estado, String temperatura,String  estadoUv,String presion,String humedad){

        SQLiteDatabase BaseDeDatos3 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("dia", dia);
        cv.put("estado", estado);
        cv.put("estadoUv", estadoUv);
        cv.put("temperatura", temperatura);
        cv.put("presion", presion);
        cv.put("humedad", humedad);

        long result = BaseDeDatos3.insert("REGISTROS", null, cv);

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Se añadio correctamente", Toast.LENGTH_SHORT).show();

        }
    }
    //Funcion para ver todos los reportes
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME  + "";
        SQLiteDatabase BaseDeDatos3 = this.getReadableDatabase();
        Cursor cursor = null;

        if (BaseDeDatos3 != null){
            cursor = BaseDeDatos3.rawQuery(query, null);

        }
        return cursor;
    }

    //Funcion para semana
    Cursor readWeekData(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date diaActual = new Date();
        Calendar c  = Calendar.getInstance();
        String diaFinal = dateFormat.format(diaActual);
        c.add(Calendar.DATE,-7);
        Date cMinus7 = c.getTime();
        String diaInicio = dateFormat.format(cMinus7);

        String query = "SELECT * FROM " + TABLE_NAME  + " WHERE dia BETWEEN " + "'" + diaInicio + "'" + " AND " + "'" + diaFinal + "'" ;
        SQLiteDatabase BaseDeDatos3 = this.getReadableDatabase();
        Cursor cursor = null;

        if (BaseDeDatos3 != null){
            cursor = BaseDeDatos3.rawQuery(query, null);

        }
        return cursor;
    }

    //Funcion para lapso de tiempo

    Cursor readLapsoData(String fechaInicio, String fechaFin, int estado, int temperatura, int presion , int estadoUV, int humedad){
        String estadoS, temperaturaS, presionS, estadoUVS, humedadS;

        if(estado == 1){
            estadoS = ",estado";
        }else{
            estadoS = "";
        }

        if(temperatura == 1){
            temperaturaS = ",temperatura";
        }else{
            temperaturaS = "";
        }

        if(estadoUV == 1){
            estadoUVS = ", estadoUv";
        }else{
            estadoUVS = "";
        }

        if(presion == 1){
            presionS = ", presion";
        }else{
            presionS = "";
        }

        if(humedad == 1){
            humedadS = ", humedad";
        }else{
            humedadS = "";
        }

        String query = "SELECT * FROM " + TABLE_NAME  + " WHERE dia BETWEEN " + "'" + fechaInicio+"' " + " AND " + "'"+ fechaFin + "'";

        SQLiteDatabase BaseDeDatos3 = this.getReadableDatabase();
        Cursor cursor = null;

        if (BaseDeDatos3 != null){
            cursor = BaseDeDatos3.rawQuery(query, null);

        }
        return cursor;
    }
    //Funcion para leer registros del dia de hoy
    Cursor readTodayData(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String diaActual = dateFormat.format(date);

        String query = "SELECT * FROM " + TABLE_NAME  + " WHERE dia = '" + diaActual +  "'" ;
        SQLiteDatabase BaseDeDatos3 = this.getReadableDatabase();
        Cursor cursor = null;

        if (BaseDeDatos3 != null){
            cursor = BaseDeDatos3.rawQuery(query, null);

        }
        return cursor;
    }

}
