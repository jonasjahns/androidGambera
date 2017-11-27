package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;

import android.app.ListActivity;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.contas.ContaAdapter;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
import android.jonas.edu.meugerenciadorfinanceiro.dao.LancamentoDao;
import android.os.Bundle;

import java.util.ArrayList;

public class ListarLancamentoActivity extends ListActivity {

    ArrayList<Lancamento> lancamentos;
    LancamentoDao lancamentoDao = new LancamentoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_lancamento);
        lancamentos = lancamentoDao.getAll(this);
        setListAdapter(new LancamentoAdapter(this, lancamentos));
    }
}
