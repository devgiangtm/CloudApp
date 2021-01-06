package com.example.cloudapp;

import android.content.Context;

import com.example.cloudapp.model.Command;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

public class DAOManager {
    private static Dao<Command,Long> commandDao = null;

    public static Dao<Command,Long> getCommandDao(Context context) {
        if (commandDao==null) {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            AndroidConnectionSource androidConnectionSource = new AndroidConnectionSource(databaseHelper);
            try {
                return DaoManager.createDao(androidConnectionSource, Command.class);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return commandDao;
    }
}
