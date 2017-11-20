package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;

import java.util.ArrayList;

public class EditarContaActivity extends AppCompatActivity {

    ArrayList<Conta> contas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);

        contas = getIntent().getParcelableArrayListExtra(InicioActivity.PAR_CONTAS);


    }
}
