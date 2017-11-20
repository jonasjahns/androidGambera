package android.jonas.edu.meugerenciadorfinanceiro;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class TipoActivity extends ListActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String[] tipos = getResources().getStringArray(R.array.tipo);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, tipos));
        String tipo = getIntent().getStringExtra("tipo");
        if (tipo != null)
        {
            int posicao = Arrays.asList(tipos).indexOf(tipo);
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(posicao,true);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicao, long id)
    {
        super.onListItemClick(l,v,posicao,id);
        String resultado = l.getItemAtPosition(posicao).toString();
        Intent it= new Intent();
        it.putExtra("resultado", resultado);
        setResult(RESULT_OK, it);
        finish();
    }
}
