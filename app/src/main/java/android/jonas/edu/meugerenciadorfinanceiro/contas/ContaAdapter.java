package android.jonas.edu.meugerenciadorfinanceiro.contas;


import android.content.Context;
import android.jonas.edu.meugerenciadorfinanceiro.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ContaAdapter extends ArrayAdapter<Conta> {
    private static class ViewHolder {
        TextView id;
        TextView numero;
        TextView saldo;
    }

    public ContaAdapter(Context context, ArrayList<Conta> contas) {
        super(context, R.layout.list_conta_row, contas);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Conta conta = getItem(position);

        ContaAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ContaAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_conta_row, parent, false);
            viewHolder.id = (TextView) convertView.findViewById(R.id.list_id);
            viewHolder.numero = (TextView) convertView.findViewById(R.id.list_numero);
            viewHolder.saldo = (TextView) convertView.findViewById(R.id.list_saldo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContaAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(conta.getId().toString());
        viewHolder.numero.setText(conta.getNumero().toString());
        viewHolder.saldo.setText(conta.getSaldo().toString());
        return convertView;
    }
}
