package android.jonas.edu.meugerenciadorfinanceiro;

import android.app.ListActivity;
import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.dao.LancamentoDao;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.Lancamento;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.LancamentoAdapter;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.LancamentoDespesa;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.LancamentoReceita;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TotalActivity extends ListActivity {

    ArrayList<Lancamento> lancamentos;
    Date dataAtual = new Date();
    TextView textoMes;
    TextView editDespesa;
    TextView editReceita;
    TextView editSaldo;
    LancamentoDao lancamentoDao = new LancamentoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        Intent intent = getIntent();

        editDespesa = (TextView) findViewById(R.id.editValorDespesa);
        editReceita = (TextView) findViewById(R.id.editValorReceita);
        editSaldo = (TextView) findViewById(R.id.editSaldo);
        ImageButton setaEsquerda = (ImageButton) findViewById(R.id.setaEsquerda);
        ImageButton setaDireita = (ImageButton) findViewById(R.id.setaDireita);
        textoMes = (TextView) findViewById(R.id.textMes);
        lancamentos = lancamentoDao.getAll(this);
        atualizarTotais(filtrarLancamento(dataAtual, lancamentos));
        atualizarMes(textoMes);
        setListAdapter(new LancamentoAdapter(this, filtrarLancamento(dataAtual, lancamentos)));
    }
    ArrayList<Lancamento> filtrarLancamento(Date dataFiltro, ArrayList<Lancamento> lancamentos)
    {
        DateFormat formatoMes = new SimpleDateFormat("MM/yyyy");
        ArrayList<Lancamento> lancamentosFiltrados = new ArrayList<Lancamento>();
        for(Lancamento lancamento : lancamentos)
        {
            Toast.makeText(this, "Lancamentos data: " + lancamento.getDataLancamentoFormatada(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Lancamentos data: " + lancamento.getDataLancamento(), Toast.LENGTH_SHORT).show();
            if (formatoMes.format(lancamento.getDataLancamento()).equals(formatoMes.format(dataFiltro)))
            {
                lancamentosFiltrados.add(lancamento);
            }
        }

        return lancamentosFiltrados;
    }

    public void executarBotaoDireitaOnClick(View v)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);
        calendar.add(Calendar.MONTH, +1);
        dataAtual = calendar.getTime();
        atualizarMes(textoMes);
        atualizarTotais(filtrarLancamento(dataAtual, lancamentos));
        setListAdapter(new LancamentoAdapter(this, filtrarLancamento(dataAtual, lancamentos)));
    }

    public void executarBotaoEsquerdaOnClick(View v)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataAtual);
        calendar.add(Calendar.MONTH, -1);
        dataAtual = calendar.getTime();
        atualizarMes(textoMes);
        atualizarTotais(filtrarLancamento(dataAtual, lancamentos));
        setListAdapter(new LancamentoAdapter(this, filtrarLancamento(dataAtual, lancamentos)));
    }

    public void atualizarMes(TextView textoMes)
    {
        Locale local = new Locale("pt", "BR");
        DateFormat df2 = new SimpleDateFormat("MMMM", local);
        textoMes.setText(df2.format(dataAtual));
    }
    public void atualizarTotais(ArrayList<Lancamento> lancamentos)
    {
        BigDecimal despesa = new BigDecimal(0);
        BigDecimal receita = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);
        for (Lancamento lancamento : lancamentos)
        {
            if (lancamento.getSituacao().equals("Recebido") || lancamento.getSituacao().equals("Não Recebido"))
            {
                receita = receita.add(lancamento.getValorLancamento());
            }
            else if (lancamento.getSituacao().equals("Pago") || lancamento.getSituacao().equals("Não Pago")) {
                despesa = despesa.add(lancamento.getValorLancamento());
            }
        }
        editDespesa.setText(despesa.toString());
        editReceita.setText(receita.toString());
        total = total.add(receita);
        total = total.subtract(despesa);
        editSaldo.setText(total.toString());
    }
}
