package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
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
    ContaDao contaDao;
    EditText editContaNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        contas = getIntent().getParcelableArrayListExtra(InicioActivity.PAR_CONTAS);
        editContaNumero = (EditText) findViewById(R.id.editContaNumero);

    }

    public void salvarOnClick (View view)
    {
        Integer numero = new Integer(editContaNumero.getText().toString());
        Conta conta = new Conta(null, numero, new BigDecimal(0));
        contaDao.insert(conta,this);
        valorPadrao();
    }

    public void valorPadrao()
    {
        editContaNumero.setText("");
    }

    public  void voltarOnClick (View view)
    {
        finish();
    }
}

