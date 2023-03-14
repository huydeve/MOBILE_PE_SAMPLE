package com.example.pe_se150294;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClockProvider extends ContentProvider {
    final String DB = "ClockStore";
    static final String PROVIDER = "com.example.pe_se150294.ClockProvider";
    static final String TABLE = "Clock";
    final String NAME = "name";
    public static final String PROVIDER_URI = "content://"+PROVIDER+"/"+TABLE;

    final String ID = "id";

    final String PRICE = "price";
    final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE + "( " +
                    ID + " INTEGER NOT NULL PRIMARY KEY " +
                    "AUTOINCREMENT, " +
                    NAME +" TEXT NOT NULL, " +
                    PRICE + " INTEGER NOT NULL " +
                    ")";
    final int MyUriCode = 1;
    SQLiteDatabase MyDB;
    UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, TABLE, MyUriCode);
        MyDB = getContext().openOrCreateDatabase(DB, Context.MODE_PRIVATE, null);
        MyDB.execSQL(CREATE_TABLE);

        return MyDB != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case MyUriCode:
                cursor = MyDB.query(TABLE, projection, selection, selectionArgs, null,
                        null, (sortOrder == null)? ID : sortOrder);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            default:
                throw new IllegalArgumentException("UnKnown URI: " + uri.toString());
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MyUriCode:
                return PROVIDER + "/" + TABLE;
            default:
                throw new IllegalArgumentException("UnKnown URI: " + uri.toString());
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId = 0;
        switch (uriMatcher.match(uri)) {
            case MyUriCode:
                rowId = MyDB.insert(TABLE, "", values);
                Uri newURI = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(newURI, null);
                return newURI;

            default:
                throw new IllegalArgumentException("UnKnown URI: " + uri.toString());
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MyUriCode:
                count = MyDB.delete(TABLE, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("UnKnown URI: " + uri.toString());
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MyUriCode:
                count = MyDB.update(TABLE, values,selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("UnKnown URI: " + uri.toString());
        }
        return count;
    }
}
