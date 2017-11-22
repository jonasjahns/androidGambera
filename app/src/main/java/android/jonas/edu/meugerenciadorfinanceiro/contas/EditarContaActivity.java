package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditarContaActivity extends AppCompatActivity {

    ArrayList<Conta> contas;
    TextView exibeContaId;
    TextView exibeContaSaldo;
    EditText editContaNumero;
    ContaDao contaDao = new ContaDao();
    Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);
        exibeContaId = (TextView) findViewById(R.id.exibeContaId);
        exibeContaSaldo = (TextView) findViewById(R.id.exibeContaSaldo);
        editContaNumero = (EditText) findViewById(R.id.editContaNumero);
        String strId = getIntent().getStringExtra("idConta");
        atualizarValores();
    }
    public void excluirOnClick(View view)
    {
        contaDao.deleteById(this, new Long(conta.getId()));
        Toast.makeText(this, "Conta deletada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void salvarOnClick(View view)
    {
        Long idConta = new Long (editContaNumero.getText().toString());
        conta.setNumero(idConta.intValue());
        contaDao.update(conta, this);
        Toast.makeText(this, "Conta atualizada com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void atualizarValores()
    {
        conta = contaDao.getById(this, conta.getId());
        editContaNumero.setText(conta.getNumero().toString());
    }
}
