package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.app.ListActivity;
import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;

import java.util.ArrayList;

public class ListarContaActivity extends ListActivity {

    ArrayList<Conta> contas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_conta);
        Intent intent = getIntent();
        contas = getIntent().getParcelableArrayListExtra(InicioActivity.PAR_CONTAS);
        setListAdapter(new ContaAdapter(this, contas));
    }
}
