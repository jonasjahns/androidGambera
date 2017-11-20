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

public class SituacaoActivity extends ListActivity {
/*
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String[] situacoes = getResources().getStringArray(R.array.situacao);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, situacoes));
        String situacao = getIntent().getStringExtra("situacao");
        if (situacao != null)
        {
            int posicao = Arrays.asList(situacoes).indexOf(situacao);
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
    }*/
}
