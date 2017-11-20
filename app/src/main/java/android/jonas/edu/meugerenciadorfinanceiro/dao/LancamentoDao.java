package android.jonas.edu.meugerenciadorfinanceiro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.lancamentos.Lancamento;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.ClassesContrato;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.ContaSqlHelper;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.LancamentoSqlHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jonas on 20/11/2017.
 */

public class LancamentoDao {

    public void insert(Lancamento lancamento, Context context) {
        LancamentoSqlHelper lancamentoSqlHelper = new LancamentoSqlHelper(context);

        SQLiteDatabase db = lancamentoSqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO, lancamento.getDescricao());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA, lancamento.getCategoria());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO, lancamento.getSituacao());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO, lancamento.getDataCriacaoFormatada());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO, lancamento.getDataLancamentoFormatada());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO, lancamento.getValorLancamento().doubleValue());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS, lancamento.getNumeroParcelas());
        contentValues.put(ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI, lancamento.getCodigo_pai());


        long id = db.insert(ClassesContrato.Lancamento.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Conta> getAll(Context context) {
        ContaSqlHelper contaSqlHelper = new ContaSqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getReadableDatabase();

        String[] projection = {
                ClassesContrato.Conta._ID,
                ClassesContrato.Conta.COLUMN_NAME_NUMERO,
                ClassesContrato.Conta.COLUMN_NAME_SALDO,
        };
        ArrayList<Conta> contas = new ArrayList<>();

        String sortOrder = ClassesContrato.Conta.TABLE_NAME + " ASC";

        return  contas;
    }
}

