package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.jonas.edu.meugerenciadorfinanceiro.InicioActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditarContaActivity extends AppCompatActivity {

    ArrayList<Conta> contas;
    TextView exibeContaId;
    TextView exibeContaSaldo;
    EditText editContaNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);
        exibeContaId = (TextView) findViewById(R.id.exibeContaId);
        exibeContaSaldo = (TextView) findViewById(R.id.exibeContaSaldo);
        editContaNumero = (EditText) findViewById(R.id.editContaNumero);
    }

    public void excluirOnClick(View view)
    {

    }
}
