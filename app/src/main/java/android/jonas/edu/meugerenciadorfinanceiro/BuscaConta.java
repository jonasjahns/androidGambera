package android.jonas.edu.meugerenciadorfinanceiro;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.contas.EditarContaActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BuscaConta extends AppCompatActivity {

    EditText editBuscarCota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_conta);
        editBuscarCota = (EditText) findViewById(R.id.editBuscarConta);
    }

    public void buscarOnClick(View view)
    {
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("idConta", editBuscarCota.getText().toString());
        startActivity(it);
    }
}
