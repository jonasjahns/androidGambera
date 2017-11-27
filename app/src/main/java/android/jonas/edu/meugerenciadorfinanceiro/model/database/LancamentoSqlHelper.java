package android.jonas.edu.meugerenciadorfinanceiro.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.Date;

public class LancamentoSqlHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ClassesContrato.Lancamento.TABLE_NAME + " ("+
            ClassesContrato.Lancamento._ID + " INTEGER PRIMARY KEY," +
            ClassesContrato.Lancamento.COLUMN_NAME_DESCRICAO+ DefinicaoDb.TEXT_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_CATEGORIA+ DefinicaoDb.TEXT_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_SITUACAO+ DefinicaoDb.TEXT_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_DATA_CRIACAO+ DefinicaoDb.TEXT_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_DATA_LANCAMENTO+ DefinicaoDb.DATETIME_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_VALOR_LANCAMENTO+ DefinicaoDb.REAL_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_NUMERO_PARCELAS+ DefinicaoDb.INTEGER_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_PAI+ DefinicaoDb.INTEGER_TYPE+ DefinicaoDb.COMMA_SEP +
            ClassesContrato.Lancamento.COLUMN_NAME_CODIGO_CONTA + DefinicaoDb.INTEGER_TYPE + DefinicaoDb.COMMA_SEP + " )";

  private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ClassesContrato.Lancamento.TABLE_NAME;

   public LancamentoSqlHelper(Context context) {
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
