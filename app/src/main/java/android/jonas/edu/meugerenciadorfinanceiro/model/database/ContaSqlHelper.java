package android.jonas.edu.meugerenciadorfinanceiro.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonas on 20/11/2017.
 */

public class ContaSqlHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ClassesContrato.Conta.TABLE_NAME + " ("+
            ClassesContrato.Conta._ID + " INTEGER PRIMARY KEY," +
            ClassesContrato.Conta.COLUMN_NAME_NUMERO + DefinicaoDb.INTEGER_TYPE + DefinicaoDb.COMMA_SEP +
            ClassesContrato.Conta.COLUMN_NAME_SALDO + DefinicaoDb.REAL_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ClassesContrato.Conta.TABLE_NAME;

    public ContaSqlHelper(Context context) {
        super(context, DefinicaoDb.DATABASE_NAME, null, DefinicaoDb.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
