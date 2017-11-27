package android.jonas.edu.meugerenciadorfinanceiro;

import android.content.Intent;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.dao.ContaDao;
import android.jonas.edu.meugerenciadorfinanceiro.dao.LancamentoDao;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.Lancamento;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.LancamentoBuilder;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.LancamentoDespesa;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Lancamento> lancamentos = new ArrayList<Lancamento>();
    public static String PAR_LANCAMENTOS = "lancamentos";

    TextView textCodigo;
    EditText editDescricao;
    Button btnCategoria;
    TextView textDtCriacao;
    EditText editDtLancamento;
    EditText editValor;
    Button btnParcelas;
    Button btnTipo;
    TextView textParcelas;
    LancamentoDao lancamentoDao = new LancamentoDao();
    ContaDao contaDao = new ContaDao();
    Conta conta;

    Date data = new Date();
    private Integer codLancamento = 0;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat compara = new SimpleDateFormat("MM/yyyy");

    private static final int REQUEST_CATEGORIA = 1;
    private static final int REQUEST_PARCELAS = 2;
    private static final int REQUEST_TIPO = 3;
    private static final int REQUEST_CONSULTAR = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        Button btnTotais = (Button) findViewById(R.id.btnTotais);
        Button btnConsultar = (Button) findViewById(R.id.btnConsultar);
        Button btnExcluir = (Button) findViewById(R.id.btnExcluir);

        textCodigo = (TextView) findViewById(R.id.textCodigo);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        btnCategoria = (Button) findViewById(R.id.btnCategoria);
        textDtCriacao = (TextView) findViewById(R.id.textDtCriacao);
        editDtLancamento = (EditText) findViewById(R.id.editDtLancamento);
        editValor = (EditText) findViewById(R.id.editValor);
        btnParcelas = (Button) findViewById(R.id.btnParcelas);
        btnTipo = (Button) findViewById(R.id.btnTipo);
        textParcelas = (TextView) findViewById(R.id.textParcelas);


        textDtCriacao.setText(df.format(data));

        btnSalvar.setOnClickListener(this);
        btnCategoria.setOnClickListener(this);
        btnTipo.setOnClickListener(this);
        btnParcelas.setOnClickListener(this);
        btnTotais.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);
        btnExcluir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent it;
        switch (v.getId()) {
            case R.id.btnSalvar:
                List<String> erros = new ArrayList<>();
                if (editDescricao.getText().toString().equals("")) {
                    erros.add("Descrição");
                }
                if (btnCategoria.getText().toString().equals("Categoria")) {
                    erros.add("Categoria");
                }
                if (editDtLancamento.getText().toString().equals("")) {
                    erros.add("Data Lançamento");
                }
                if (editValor.getText().toString().equals("")) {
                    erros.add("Valor");
                }
                if (btnParcelas.getText().toString().equals("Parcelas") && btnTipo.getText().toString().equals("Despesa")) {
                    erros.add("Parcelas");
                }
                if (btnTipo.getText().toString().equals("Selecione")) {
                    erros.add("Tipo");
                }
                if (erros.size() == 0) {
                    Date dataAux = null;
                    Integer codAux;
                    try {
                        dataAux = df.parse(editDtLancamento.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (textCodigo.getText().toString().equals("")) {
                        codAux = this.codLancamento;
                        BigDecimal valorParcela = new BigDecimal(editValor.getText().toString());
                        valorParcela = valorParcela.divide(new BigDecimal(btnParcelas.getText().toString()), 2);
                        List<Lancamento> newLancamentos = new LancamentoBuilder()
                                .setCodigo(codAux)
                                .setDescricao(editDescricao.getText().toString())
                                .setCategoria(btnCategoria.getText().toString())
                                .setDataCriacao(new Date())
                                .setDataLancamento(dataAux)
                                .setValorLancamento(valorParcela)
                                .setNumeroParcelas(new Integer(btnParcelas.getText().toString()))
                                .setCodigoConta(new Integer(getIntent().getStringExtra("idConta")))
                                .createLancamento(btnTipo.getText().toString());
                        lancamentos.addAll(newLancamentos);
                        for (Lancamento lancamento : lancamentos)
                        {
                            lancamentoDao.insert(lancamento, this);
                        }
                    } else {
                        codAux = new Integer(textCodigo.getText().toString());
                        Lancamento editLancamento = lancamentoDao.getById(this, codAux);
                        editLancamento.setCategoria((btnCategoria.getText().toString()));
                        editLancamento.setDataLancamento(dataAux);
                        editLancamento.setDescricao(editDescricao.getText().toString());
                        editLancamento.setValorLancamento(new BigDecimal(editValor.getText().toString()));
                        lancamentoDao.update(this, editLancamento);
                    }
                    BigDecimal doubleAux = new BigDecimal(editValor.getText().toString());


                    tornarValorPadrao();
                    Toast.makeText(this, "Lançamento salvo!", Toast.LENGTH_SHORT).show();

                } else {
                    for (String erro : erros) {
                        Toast.makeText(this, "Favor Preencher o campo " + erro, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnCategoria:
                it = new Intent(this, CategoriaActivity.class);
                if (!(btnCategoria.getText().toString().equals("Categoria"))) {
                    it.putExtra("categoria", btnCategoria.getText().toString());
                }
                if (!(btnTipo.getText().toString().equals("Selecione"))) {
                    it.putExtra("tipo", btnTipo.getText().toString());
                } else {
                    Toast.makeText(this, "Selecione o tipo", Toast.LENGTH_SHORT).show();
                }
                startActivityForResult(it, REQUEST_CATEGORIA);
                break;
            case R.id.btnTipo:
                it = new Intent(this, TipoActivity.class);
                if (!(btnTipo.getText().toString().equals("Selecione"))) {
                    it.putExtra("tipo", btnTipo.getText().toString());
                }
                startActivityForResult(it, REQUEST_TIPO);
                break;
            case R.id.btnParcelas:
                it = new Intent(this, ParcelasActivity.class);
                if (!(btnParcelas.getText().toString().equals("Parcelas"))) {
                    it.putExtra("numeroParcela", btnParcelas.getText().toString());
                }
                startActivityForResult(it, REQUEST_PARCELAS);
                break;
            case R.id.btnTotais:
                it = new Intent(this, TotalActivity.class);
                startActivity(it);
                break;
            case R.id.btnConsultar:
                it = new Intent(this, ConsultarActivity.class);
                startActivityForResult(it, REQUEST_CONSULTAR);
                break;
            case R.id.btnExcluir:
                if (textCodigo.getText().toString() != "") {
                    lancamentoDao.deleteByIdAndConta(this, new Long(textCodigo.getText().toString()), new Integer(getIntent().getStringExtra("idConta")));
                    tornarValorPadrao();
                    Toast.makeText(this, "Lancamento deletado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Não há lançamento selecionado", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CATEGORIA) {
            btnCategoria.setText(data.getStringExtra("resultado"));
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_TIPO) {
            btnTipo.setText(data.getStringExtra("resultado"));
            if (data.getStringExtra("resultado").equals("Despesa")) {
                btnParcelas.setVisibility(View.VISIBLE);
                textParcelas.setVisibility(View.VISIBLE);
            }
            if (data.getStringExtra("resultado").equals("Receita")) {
                btnParcelas.setVisibility(View.INVISIBLE);
                textParcelas.setVisibility(View.INVISIBLE);
                btnParcelas.setText("1");
            }
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_CONSULTAR) {
            String codigoStr = data.getStringExtra("codigo");
            Integer codigo = new Integer(codigoStr.toString());
            Lancamento lancamento = lancamentoDao.getByIdAndConta(this, codigo, new Integer(getIntent().getStringExtra("idConta")));
            if (lancamento != null) {
                textCodigo.setText(lancamento.getCodigo().toString());
                editDescricao.setText(lancamento.getDescricao());
                btnCategoria.setText(lancamento.getCategoria());
                textDtCriacao.setText(df.format(lancamento.getDataCriacao()));
                editDtLancamento.setText(df.format(lancamento.getDataLancamento()));
                editValor.setText(lancamento.getValorLancamento().toString());
                btnParcelas.setText(lancamento.getNumeroParcelas().toString());
                if (lancamento.getClass() == LancamentoDespesa.class) {
                    btnTipo.setText("Despesa");
                } else {
                    btnTipo.setText("Receita");
                }

            } else {
                Toast.makeText(this, "Lançamento não encontrado", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_PARCELAS) {
            btnParcelas.setText(data.getStringExtra("resultado"));
        }
    }

    public void tornarValorPadrao() {
        textCodigo.setText("");
        editDescricao.setText("");
        btnCategoria.setText("Categoria");
        textDtCriacao.setText(df.format(data));
        editDtLancamento.setText("");
        editValor.setText("");
        btnParcelas.setText("1");
        btnTipo.setText("Selecione");
    }
}
