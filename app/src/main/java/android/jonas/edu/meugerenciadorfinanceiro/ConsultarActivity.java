package android.jonas.edu.meugerenciadorfinanceiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editCodigoConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);


        editCodigoConsulta = (EditText) findViewById(R.id.editCodigoConsulta);
        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        Button btnLimpar = (Button) findViewById(R.id.btnLimpar);



        btnBuscar.setOnClickListener(this);
        btnLimpar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnBuscar:
                if(editCodigoConsulta.getText().toString().equals("")){
                    Toast.makeText(this, "Favor informar um código", Toast.LENGTH_SHORT).show();
                }else {
                    Intent it = new Intent();
                    String codigo = editCodigoConsulta.getText().toString();
                    it.putExtra("codigo", codigo);
                    setResult(RESULT_OK, it);
                    finish();
                }
                break;
            case R.id.btnLimpar:
                editCodigoConsulta.setText("");
                break;
        }
    }
}
