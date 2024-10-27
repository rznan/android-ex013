package br.com.renan.ex013;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 *@author: renan santos carvalho
 */
public class MainActivity extends AppCompatActivity {


    private TextView tvLog;
    private EditText etCpf;
    private EditText etNome;
    private Button btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnBuscar = findViewById(R.id.btnBuscar);
        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        tvLog = findViewById(R.id.tvLog);
        
        btnBuscar.setOnClickListener(e -> search());
    }

    private void search() {
        String nome = etNome.getText().toString();
        String cpf = etCpf.getText().toString().replaceAll("[^\\d]", "");

        if(!validateCPF(cpf)) {
            tvLog.setText("CPF INVÁLIDO");
            return;
        }
        tvLog.setText("");

        Bundle b = new Bundle();
        b.putString("cpf", cpf);
        b.putString("nome", nome);

        Intent i = new Intent(this, OutputActivity.class);
        i.putExtras(b);
        this.startActivity(i);
        this.finish();
    }

    private Boolean validateCPF(String cpf) {
        if(cpf.length() != 11) {
            return false;
        }
        return true;
    }
}
