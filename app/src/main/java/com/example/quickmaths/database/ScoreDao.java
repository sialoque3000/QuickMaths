package com.example.quickmaths.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.quickmaths.entities.Score;

public class ScoreDao extends BaseDao<Score> {
    public static String score= "SCORE";
    public static String name= "NAME";

    public static String tableName = "SCORE_PLAYER";

    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(score,entity.getScore());
        values.put(name,entity.getName());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Score calcul = new Score();
        Integer indexScore = cursor.getColumnIndex(score);
        calcul.setScore(cursor.getInt(indexScore));
        Integer indexName = cursor.getColumnIndex(name);
        calcul.setName(cursor.getString(indexName));
        return calcul;
    }

    @Override
    protected String getScoreColumnName() {
        return "SCORE";
    }
}