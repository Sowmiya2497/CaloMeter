package com.example.android.calometer.data;

import android.provider.BaseColumns;

/**
 * Created by subrafive on 20-08-2017.
 */

public final class AppContract {
    private AppContract() {}

    public static final class UserEntry implements BaseColumns{
        public final static String TABLE_NAME = "user_auth";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME = "user_name";
        public final static String COLUMN_USER_PASSWORD = "password";
    }
}
