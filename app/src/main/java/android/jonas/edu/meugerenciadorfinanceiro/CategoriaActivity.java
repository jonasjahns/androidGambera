package android.jonas.edu.meugerenciadorfinanceiro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class CategoriaActivity extends ListActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String[] categorias = getResources().getStringArray(R.array.categorias);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, categorias));
        String categoria = getIntent().getStringExtra("categoria");
        if (categoria != null)
        {
            int posicao = Arrays.asList(categorias).indexOf(categoria);
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
