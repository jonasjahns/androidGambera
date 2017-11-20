package android.jonas.edu.meugerenciadorfinanceiro;

import android.app.ListActivity;
import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by Jonas on 23/10/2017.
 */

public class ParcelasActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] parcelas = getResources().getStringArray(R.array.parcelas);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, parcelas));
        String numeroParcela = getIntent().getStringExtra("numeroParcela");

        if (numeroParcela != null) {
            int posicao = Arrays.asList(parcelas).indexOf(numeroParcela);
            getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(posicao, true);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int posicao, long id) {
        super.onListItemClick(l, v, posicao, id);
        String resultado = l.getItemAtPosition(posicao).toString();
        Intent it = new Intent();
        it.putExtra("resultado", resultado);
        setResult(RESULT_OK, it);
        finish();
    }
}

