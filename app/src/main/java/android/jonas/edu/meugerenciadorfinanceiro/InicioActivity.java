package android.jonas.edu.meugerenciadorfinanceiro;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.contas.CriarContaActivity;
import android.jonas.edu.meugerenciadorfinanceiro.contas.EditarContaActivity;
import android.jonas.edu.meugerenciadorfinanceiro.contas.ListarContaActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    public static String PAR_CONTAS = "contas";
    public static String codigoContas = "condigoContas";

    private static final int REQUEST_CRIAR = 51;
    private static final int REQUEST_EDITAR = 52;

    private Integer codContas = 0;

    Button btnContaListar;
    Button btnContaEditar;
    Button btnContaExcluir;
    Button btnContaCriar;
    Button btnLancamentos;
    ArrayList<Conta> contas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnContaListar = (Button) findViewById(R.id.btnContaListar);
        btnContaEditar = (Button) findViewById(R.id.btnContaEditar);
        btnContaCriar = (Button) findViewById(R.id.btnContaCriar);
        btnLancamentos = (Button) findViewById(R.id.btnLancamentos);

        btnContaListar.setOnClickListener(this);
        btnContaEditar.setOnClickListener(this);
        btnContaExcluir.setOnClickListener(this);
        btnContaCriar.setOnClickListener(this);
        btnLancamentos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it;
        switch (v.getId()) {
            case R.id.btnContaCriar:
                it = new Intent(this, CriarContaActivity.class);
                startActivity(it);
                break;
            case R.id.btnContaListar:
                it = new Intent(this, ListarContaActivity.class);
                startActivity(it);
                break;
            case R.id.btnContaConsultar:
                it = new Intent(this, EditarContaActivity.class);
                startActivity(it);
                break;
        }
    }
}