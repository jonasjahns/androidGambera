package android.jonas.edu.meugerenciadorfinanceiro.contas;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Jonas on 23/10/2017.
 */

public class Conta implements  Parcelable{
    Long id;
    Integer numero;
    BigDecimal saldo;

    public static final Parcelable.Creator<Conta> CREATOR = new Parcelable.Creator<Conta>() {
        @Override
        public Conta createFromParcel(Parcel source) {
            return new Conta(source);
        }

        @Override
        public Conta[] newArray(int size) {
            return new Conta[size];
        }
    };

    Conta(Parcel parcel)
    {
        id = new Long(parcel.readString());
        numero = new Integer(parcel.readString());
        saldo = new BigDecimal(parcel.readString());
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id.toString());
        dest.writeString(this.numero.toString());
        dest.writeString(this.saldo.toString());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Conta(Long id, Integer numero, BigDecimal saldo) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
    }
}
