package com.example.quickmaths.database;

import android.content.Context;

public class CalculBaseHelper extends DataBaseHelper {
    public CalculBaseHelper(Context context, String dataBaseName, int dataBaseVersion) {
        super(context, dataBaseName, dataBaseVersion);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + ScoreDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                ScoreDao.score + " INTEGER NOT NULL," +
                ScoreDao.name + " VARCHAR(50) NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE IF EXISTS "+ScoreDao.tableName;
    }
}