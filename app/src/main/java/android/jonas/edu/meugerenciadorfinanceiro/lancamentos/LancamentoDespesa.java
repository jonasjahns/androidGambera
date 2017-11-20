package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;


import java.math.BigDecimal;
import java.util.Date;

public class LancamentoDespesa extends Lancamento {
    public LancamentoDespesa(Integer codigo, String descricao, String categoria, String situacao, Date dataCriacao, Date dataLancamento, BigDecimal valorLancamento, Integer numeroParcelas, Integer codigo_pai) {
        super(codigo, descricao, categoria, situacao, dataCriacao, dataLancamento, valorLancamento, numeroParcelas, codigo_pai);
    }

    public LancamentoDespesa(Integer codigo, String descricao, String categoria, String situacao, Date dataCriacao, Date dataLancamento, BigDecimal valorLancamento, Integer numeroParcelas) {
        super(codigo, descricao, categoria, situacao, dataCriacao, dataLancamento, valorLancamento, numeroParcelas);

    }
}
