package android.jonas.edu.meugerenciadorfinanceiro.lancamentos;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Lancamento implements  Parcelable{
    Integer codigo;
    String descricao;
    String categoria;
    String situacao;
    Date dataCriacao;
    Date dataLancamento;
    BigDecimal valorLancamento;
    Integer numeroParcelas;
    Integer codigo_pai;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static final Creator<Lancamento> CREATOR = new Creator<Lancamento>() {
        @Override
        public Lancamento createFromParcel(Parcel source) {
            return new Lancamento(source);
        }

        @Override
        public Lancamento[] newArray(int size) {
            return new Lancamento[size];
        }
    };

    private Lancamento(Parcel parcel){
        try {
            dataLancamento = df.parse(parcel.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        descricao = parcel.readString();
        valorLancamento = new BigDecimal(parcel.readString());
        situacao = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(df.format(dataLancamento));
        dest.writeString(descricao);
        dest.writeString(valorLancamento.toString());
        dest.writeString(situacao);
    }


    public Integer getCodigo_pai() {
        return codigo_pai;
    }

    public void setCodigo_pai(Integer codigo_pai) {
        this.codigo_pai = codigo_pai;
    }

    public Lancamento(Integer codigo, String descricao, String categoria, String situacao, Date dataCriacao, Date dataLancamento, BigDecimal valorLancamento, Integer numeroParcelas, Integer codigo_pai) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.situacao = situacao;
        this.dataCriacao = dataCriacao;
        this.dataLancamento = dataLancamento;
        this.valorLancamento = valorLancamento;
        this.numeroParcelas = numeroParcelas;
        this.codigo_pai = codigo_pai;
    }

    public Lancamento(Integer codigo, String descricao, String categoria, String situacao, Date dataCriacao, Date dataLancamento, BigDecimal valorLancamento, Integer numeroParcelas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.situacao = situacao;
        this.dataCriacao = dataCriacao;
        this.dataLancamento = dataLancamento;
        this.valorLancamento = valorLancamento;
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public BigDecimal getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
