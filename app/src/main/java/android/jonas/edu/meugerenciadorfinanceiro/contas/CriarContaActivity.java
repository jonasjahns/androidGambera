package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CriarContaActivity extends AppCompatActivity {

    ArrayList<Conta> contas;
    Integer codContas;
    EditText editContaNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        contas = getIntent().getParcelableArrayListExtra(InicioActivity.PAR_CONTAS);
        codContas = getIntent().getIntExtra(InicioActivity.codigoContas, 0);
        editContaNumero = (EditText) findViewById(R.id.editContaNumero);

    }

    public void salvarOnClick (View view)
    {
        codContas++;
        Integer numero = new Integer(editContaNumero.getText().toString());
        Conta conta = new Conta(codContas, numero, new BigDecimal(0));
        contas.add(conta);
        valorPadrao();
    }

    public void valorPadrao()
    {
        editContaNumero.setText("");
    }

    public  void voltarOnClick (View view)
    {
        Intent it= new Intent();
        Toast.makeText(this, "Tamanho da Conta: " + contas.size(), Toast.LENGTH_SHORT).show();
        it.putParcelableArrayListExtra(InicioActivity.PAR_CONTAS, contas);
        it.putExtra(InicioActivity.codigoContas, codContas);
        setResult(RESULT_OK, it);
        finish();
    }
}

