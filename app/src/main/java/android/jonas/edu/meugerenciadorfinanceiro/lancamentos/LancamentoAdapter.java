package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;

import android.content.Context;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Jonas on 02/10/2017.
 */
public class LancamentoAdapter extends ArrayAdapter<Lancamento> {

    private static class ViewHolder{
        TextView dia;
        TextView descricao;
        TextView valor;
    }

    public LancamentoAdapter(Context context, ArrayList<Lancamento> lancamentos) {
        super(context, R.layout.list_lancamentos_row, lancamentos);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Lancamento lancamento = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_lancamentos_row,parent,false);
            viewHolder.dia = (TextView) convertView.findViewById(R.id.list_dia);
            viewHolder.descricao = (TextView) convertView.findViewById(R.id.list_descricao);
            viewHolder.valor = (TextView) convertView.findViewById(R.id.list_valor);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DateFormat formatar = new SimpleDateFormat("dd");
        viewHolder.dia.setText(String.valueOf(formatar.format(lancamento.getDataLancamento())));
        viewHolder.descricao.setText(lancamento.getDescricao());
        viewHolder.valor.setText(String.valueOf(lancamento.getValorLancamento()));

        return convertView;
    }
}
