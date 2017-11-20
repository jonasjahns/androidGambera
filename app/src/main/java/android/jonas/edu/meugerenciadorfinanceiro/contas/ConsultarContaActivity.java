package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ConsultarContaActivity extends AppCompatActivity {
    EditText editContaCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_conta);
        editContaCodigo = (EditText) findViewById(R.id.editContaCodigo);
    }

    public void buscarOnClick(View view)
    {
        Intent it = new Intent(this, EditarContaActivity.class);
        it.putExtra("idConta", editContaCodigo.getText());
        startActivity(it);
    }
}
