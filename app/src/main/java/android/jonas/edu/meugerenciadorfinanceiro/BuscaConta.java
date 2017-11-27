package android.jonas.edu.meugerenciadorfinanceiro;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.contas.EditarContaActivity;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuscaConta extends AppCompatActivity {

    EditText editBuscarCota;
    ContaDao contaDao = new ContaDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_conta);
        editBuscarCota = (EditText) findViewById(R.id.editBuscarConta);
    }

    public void buscarOnClick(View view)
    {
        if(contaDao.getById(this, new Long(editBuscarCota.getText().toString())) != null) {
            Intent it = new Intent(this, MainActivity.class);
            it.putExtra("idConta", editBuscarCota.getText().toString());
            startActivity(it);
        }
        else
        {
            Toast.makeText(this, "Favor informar um código válido", Toast.LENGTH_SHORT).show();
        }
    }
}
