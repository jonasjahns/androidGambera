package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.app.ListActivity;
import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;

import java.util.ArrayList;

public class ListarContaActivity extends ListActivity {

    ArrayList<Conta> contas;
    ContaDao contaDao = new ContaDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_conta);
        contas = contaDao.getAll(this);
        setListAdapter(new ContaAdapter(this, contas));
    }
}
