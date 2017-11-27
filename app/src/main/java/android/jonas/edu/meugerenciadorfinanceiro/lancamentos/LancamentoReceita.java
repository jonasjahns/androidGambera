package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;


import java.math.BigDecimal;
import java.util.Date;

public class LancamentoReceita extends Lancamento {
    public LancamentoReceita(Integer codigo, String descricao, String categoria, String situacao, Date dataCriacao, Date dataLancamento, BigDecimal valorLancamento, Integer numeroParcelas, Integer codigoConta) {
        super(codigo, descricao, categoria, situacao, dataCriacao, dataLancamento, valorLancamento, numeroParcelas, codigoConta);
    }
}
