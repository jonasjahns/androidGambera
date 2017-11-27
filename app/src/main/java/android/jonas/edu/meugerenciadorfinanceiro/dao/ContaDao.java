package android.jonas.edu.meugerenciadorfinanceiro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.ClassesContrato;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.SqlHelper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Created by Jonas on 20/11/2017.
 */

public class ContaDao {

    public void insert(Conta conta, Context context) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassesContrato.Conta.COLUMN_NAME_NUMERO, conta.getNumero());
        Double saldoDb = new Double(conta.getSaldo().toString());
        contentValues.put(ClassesContrato.Conta.COLUMN_NAME_SALDO, 0);
        long id = db.insert(ClassesContrato.Conta.TABLE_NAME, null, contentValues);
    }

    public void update(Conta conta, Context context) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassesContrato.Conta.COLUMN_NAME_NUMERO, conta.getNumero());
        String selection = ClassesContrato.Conta._ID + " = ?";
        String[] selectionArgs = {String.valueOf(conta.getId())};
        db.update(ClassesContrato.Conta.TABLE_NAME, contentValues,selection,selectionArgs);
    }

    public ArrayList<Conta> getAll(Context context) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getReadableDatabase();

        String[] projection = {
                ClassesContrato.Conta._ID,
                ClassesContrato.Conta.COLUMN_NAME_NUMERO,
                ClassesContrato.Conta.COLUMN_NAME_SALDO,
        };
        ArrayList<Conta> contas = new ArrayList<>();

        String sortOrder = ClassesContrato.Conta._ID + " ASC";

        Cursor cursorContas = db.query(
                ClassesContrato.Conta.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (cursorContas.moveToFirst()) {
            while (cursorContas.isAfterLast() == false) {
                long itemId = cursorContas.getLong(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta._ID));
                int numero = cursorContas.getInt(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta.COLUMN_NAME_NUMERO));
                Double saldoDouble = cursorContas.getDouble(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta.COLUMN_NAME_SALDO));
                BigDecimal saldo = new BigDecimal(saldoDouble.toString());
                contas.add(new Conta(itemId, numero, saldo));
                cursorContas.moveToNext();
            }
        }
        cursorContas.close();
        return contas;
    }

    public void deleteAll(Context context) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getWritableDatabase();

        db.delete(ClassesContrato.Conta.TABLE_NAME, null, null);
    }

    public void deleteById(Context context, Long id) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getWritableDatabase();
        String selection = ClassesContrato.Conta._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(
                ClassesContrato.Conta.TABLE_NAME,
                selection,
                selectionArgs);
    }

    public Conta getById(Context context, Long id) {
        SqlHelper contaSqlHelper = new SqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getReadableDatabase();

        String[] projection = {
                ClassesContrato.Conta._ID,
                ClassesContrato.Conta.COLUMN_NAME_NUMERO,
                ClassesContrato.Conta.COLUMN_NAME_SALDO,
        };

        Conta contaDb = null;

        String selection = ClassesContrato.Conta._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        String sortOrder = ClassesContrato.Conta._ID + " ASC";

        Cursor cursorContas = db.query(
                ClassesContrato.Conta.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (cursorContas.moveToFirst()) {
            long itemId = cursorContas.getLong(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta._ID));
            int numero = cursorContas.getInt(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta.COLUMN_NAME_NUMERO));
            BigDecimal saldo = new BigDecimal(cursorContas.getColumnIndexOrThrow(ClassesContrato.Conta.COLUMN_NAME_SALDO));
            contaDb = new Conta(itemId, numero, saldo);
        }

        return contaDb;
    }
}

