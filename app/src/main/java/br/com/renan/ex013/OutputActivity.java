package br.com.renan.ex013;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 *@author: renan santos carvalho
 */
public class OutputActivity extends AppCompatActivity {


    private TextView tvOutput;
    private Button returnButton;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_output);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        bundle = i.getExtras();

        returnButton = findViewById(R.id.returnButton);
        tvOutput = findViewById(R.id.tvOutput);

        showResultOnScreen();

        returnButton.setOnClickListener(e -> back());
    }

    private void showResultOnScreen() {
        String cpf = bundle.getString("cpf");
        String estado = getEstado(cpf.charAt(8));
        if(estado == null) {
            tvOutput.setText("Algo deu errado");
            return;
        }
        tvOutput.setText(estado);
    }

    private String getEstado(char digito) {
        switch (Integer.parseInt(String.valueOf(digito))) {
            case 1: return "Distrito Federal ou Goiás ou Mato Grosso ou Mato Grosso do Sul ou Tocantins";
            case 2: return "Pará ou Amazonas ou Acre ou Amapá ou Rondônia ou Roraima";
            case 3: return "Ceará ou Maranhão ou Piauí";
            case 4: return "Pernambuco ou Rio Grande do Norte ou Paraíba ou Alagoas";
            case 5: return "Bahia ou Sergipe";
            case 6: return "Minas Gerais";
            case 7: return "Rio de janeiro ou Espirito Santo";
            case 8: return "São Paulo";
            case 9: return "Paraná ou Santa Catarina";
            case 0: return "Rio Grande do Sul";
            default: return null;
        }
    }

    private void back() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}
