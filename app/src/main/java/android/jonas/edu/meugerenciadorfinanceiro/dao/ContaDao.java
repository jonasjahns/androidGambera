package android.jonas.edu.meugerenciadorfinanceiro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.jonas.edu.meugerenciadorfinanceiro.contas.Conta;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.ClassesContrato;
import android.jonas.edu.meugerenciadorfinanceiro.model.database.ContaSqlHelper;

import java.util.ArrayList;

/**
 * Created by Jonas on 20/11/2017.
 */

public class ContaDao {

    public void insert(Conta conta, Context context) {
        ContaSqlHelper contaSqlHelper = new ContaSqlHelper(context);
        SQLiteDatabase db = contaSqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassesContrato.Conta.COLUMN_NAME_NUMERO, conta.getNumero());
        contentValues.put(ClassesContrato.Conta.COLUMN_NAME_SALDO, conta.getSaldo().doubleValue());

        long id = db.insert(ClassesContrato.Conta.TABLE_NAME, null, contentValues);
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

