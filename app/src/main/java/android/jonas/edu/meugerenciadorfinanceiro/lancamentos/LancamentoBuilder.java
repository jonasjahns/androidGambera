package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;


import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LancamentoBuilder {
    private Integer codigo;
    private String descricao;
    private String categoria;
    private String situacao;
    private Date dataCriacao;
    private Date dataLancamento;
    private BigDecimal valorLancamento;
    private Integer numeroParcelas;
    private Integer codigoConta;

    public LancamentoBuilder setCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public LancamentoBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LancamentoBuilder setCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public LancamentoBuilder setSituacao(String situacao) {
        this.situacao = situacao;
        return this;
    }

    public LancamentoBuilder setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public LancamentoBuilder setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
        return this;
    }

    public LancamentoBuilder setValorLancamento(BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
        return this;
    }

    public LancamentoBuilder setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
        return this;
    }

    public LancamentoBuilder setCodigoConta (Integer codigoConta)
    {
        this.codigoConta = codigoConta;
        return this;
    }

    public List<Lancamento> createLancamento(String tipo) {
        System.out.println("String tipo: " + tipo);
        ArrayList<Lancamento> lancamentos = new ArrayList<Lancamento>();
        if (tipo.equals("Receita")) {
            lancamentos.add(new LancamentoReceita(codigo, descricao, categoria, defineSituacao(dataLancamento, tipo), dataCriacao, dataLancamento, valorLancamento, numeroParcelas, codigoConta));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataLancamento);
            lancamentos.add(new LancamentoDespesa(codigo, descricao, categoria, defineSituacao(calendar.getTime(), tipo), dataCriacao, calendar.getTime() , valorLancamento, numeroParcelas, codigoConta));
            for (int i = 1 ; i< numeroParcelas; i++) {
                calendar.add(Calendar.MONTH, 1);
                lancamentos.add(new LancamentoDespesa(codigo+i, descricao, categoria, defineSituacao(calendar.getTime(), tipo), dataCriacao, calendar.getTime() , valorLancamento, numeroParcelas,codigo, codigoConta));
            }

        }
        return lancamentos;
    }
    public String defineSituacao(Date dataLancamento, String tipoLancamento)
    {
        Date dataAtual = new Date();
        if (dataLancamento.after(dataAtual))
        {
            if (tipoLancamento.equals("Receita"))
            {
                return "Não Recebido";
            }
            else
            {
                return "Não Pago";
            }
        }
        else
        {
            if (tipoLancamento.equals("Receita"))
            {
                return "Recebido";
            }
            else
            {
                return "Pago";
            }
        }
    }
}