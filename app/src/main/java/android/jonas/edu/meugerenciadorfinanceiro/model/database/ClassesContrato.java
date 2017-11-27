package android.jonas.edu.meugerenciadorfinanceiro.model.database;

import android.provider.BaseColumns;

/**
 * Created by Jonas on 13/11/2017.
 */

public final class ClassesContrato {

    //Evita intanciar um objeto da classe
    private ClassesContrato() {
    }

    //Criação da inner class Aluno definindo a tabela aluno
    public static class Conta implements BaseColumns {
        public static final String TABLE_NAME = "contas";
        public static final String COLUMN_NAME_NUMERO = "numero";
        public static final String COLUMN_NAME_SALDO = "saldo";
    }

    public static class Lancamento implements BaseColumns {

        public static final String TABLE_NAME = "lancamentos";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_CATEGORIA = "categoria";
        public static final String COLUMN_NAME_SITUACAO = "situacao";
        public static final String COLUMN_NAME_DATA_CRIACAO = "data_criacao";
        public static final String COLUMN_NAME_DATA_LANCAMENTO = "data_lancamento";
        public static final String COLUMN_NAME_VALOR_LANCAMENTO = "valor_lancamento";
        public static final String COLUMN_NAME_NUMERO_PARCELAS = "numero_parcelas";
        public static final String COLUMN_NAME_CODIGO_PAI = "codigo_pai";
        public static final String COLUMN_NAME_CODIGO_CONTA = "codigo_conta";
    }

}
