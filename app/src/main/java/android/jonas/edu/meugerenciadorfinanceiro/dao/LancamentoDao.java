package android.jonas.edu.meugerenciadorfinanceiro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public ArrayList<Lancamento> getAll(Context context) {
        LancamentoSqlHelper lancamentoSqlHelper = new LancamentoSqlHelper(context);
        SQLiteDatabase db = lancamentoSqlHelper.getReadableDatabase();

        String[] projection = {
                ClassesContrato.Lancamento._ID,
                ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO,
                ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA,
                ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO,
                ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO,
                ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO,
                ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO,
                ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS,
                ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI,
                ClassesContrato.Conta.COLUMN_NAME_SALDO
        };
        ArrayList<Lancamento> lancamentos = new ArrayList<>();

        String sortOrder = ClassesContrato.Lancamento._ID + " ASC";

        Cursor cursorLancamentos = db.query(
                ClassesContrato.Lancamento.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (cursorLancamentos.moveToFirst())
        {
            while (cursorLancamentos.isAfterLast() == false)
            {
                Integer codigo = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento._ID));
                String descricao = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO));
                String categoria = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA));
                String situacao = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO));
                Date dataCriacao = new Date(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO)));
                Date dataLancamento = new Date(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO)));
                BigDecimal valorLancamento = new BigDecimal(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO)));
                Integer numeroParcelas = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS));
                Integer codigo_pai = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI));


                lancamentos.add(new Lancamento (codigo, descricao, categoria, situacao, dataCriacao, dataLancamento, valorLancamento, numeroParcelas, codigo_pai));
                cursorLancamentos.moveToNext();
            }
        }

        cursorLancamentos.close();
        return  lancamentos;
    }

    public void deleteAll(Context context) {
        LancamentoSqlHelper lancamentoSqlHelper = new LancamentoSqlHelper(context);
        SQLiteDatabase db = lancamentoSqlHelper.getWritableDatabase();

        db.delete(ClassesContrato.Lancamento.TABLE_NAME, null, null);
    }

    public Lancamento getById(Context context, Integer id) {
        ContaSqlHelper contaSqlHelper = new ContaSqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getReadableDatabase();

        String[] projection = {
                ClassesContrato.Lancamento._ID,
                ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO,
                ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA,
                ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO,
                ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO,
                ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO,
                ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO,
                ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS,
                ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI,
                ClassesContrato.Conta.COLUMN_NAME_SALDO
        };

        Lancamento lancamentoDb = null;

        String selection = ClassesContrato.Lancamento._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        String sortOrder = ClassesContrato.Conta._ID+ " ASC";

        Cursor cursorLancamentos = db.query(
                ClassesContrato.Lancamento.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (cursorLancamentos.moveToFirst()){

                Integer codigo = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento._ID));
                String descricao = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO));
                String categoria = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA));
                String situacao = cursorLancamentos.getString(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO));
                Date dataCriacao = new Date(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO)));
                Date dataLancamento = new Date(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO)));
                BigDecimal valorLancamento = new BigDecimal(cursorLancamentos.getLong(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO)));
                Integer numeroParcelas = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS));
                Integer codigo_pai = cursorLancamentos.getInt(cursorLancamentos.getColumnIndexOrThrow(ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI));
                lancamentoDb = new Lancamento (codigo, descricao, categoria, situacao, dataCriacao, dataLancamento, valorLancamento, numeroParcelas, codigo_pai);
        }

        return lancamentoDb;
    }
}

