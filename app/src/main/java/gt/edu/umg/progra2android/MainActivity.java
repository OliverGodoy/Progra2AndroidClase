package gt.edu.umg.progra2android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.progra2android.BaseDatos.DBHelper;
import gt.edu.umg.progra2android.BaseDatos.DbContactos;

public class MainActivity extends AppCompatActivity {

    Button btnSaludar, btnCrearDb;
    TextView tvSaludo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //nuevo codigo
        btnSaludar = findViewById(R.id.btnSaludar);
        tvSaludo = findViewById(R.id.textView);
        btnCrearDb = findViewById(R.id.btnCrearDb);
        Button btnCrearRegistro = findViewById(R.id.btnCrearRegistro);

        btnSaludar.setOnClickListener(v -> {
            Toast.makeText(this, "Hola Oliver", Toast.LENGTH_SHORT).show(); //Envia notificación
            tvSaludo.setText("Hola Oliver");
        });

        //Listener para crear la base datos
        btnCrearDb.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if(db != null){
                Toast.makeText(this, "Base de Datos Creada", Toast.LENGTH_SHORT).show(); //Envia notificación
                new DbContactos(this).insertaContacto("Juan","12345678","ddd.com");
            }else{
                Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
            }

        });

        //crear registro llama a la activity NuevoActivity
        btnCrearRegistro.setOnClickListener(v -> {
            Toast.makeText(this, "Creando Registro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NuevoActivity.class);
            startActivity(intent);

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}