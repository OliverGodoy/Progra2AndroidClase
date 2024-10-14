package gt.edu.umg.progra2android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.progra2android.BaseDatos.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    //NuevoActivity.Java agregar elultimo archivo
    Button btnNuevo;
    EditText txtNombre, txtTelefono, txtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo);

        btnNuevo = findViewById(R.id.btnGuardar);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);

        btnNuevo.setOnClickListener(v -> {
            DbContactos dbContactos = new DbContactos(this);
            long id = dbContactos.insertaContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtEmail.getText().toString());
            if (id > 0) {
                txtNombre.setText("");
                txtTelefono.setText("");
                txtEmail.setText("");
                Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
            }

        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}