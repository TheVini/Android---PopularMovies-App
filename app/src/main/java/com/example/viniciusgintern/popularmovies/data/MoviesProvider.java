package com.example.viniciusgintern.popularmovies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class MoviesProvider extends ContentProvider {

    static final String AUTHORITY = "com.example.viniciusgintern.popularmovies.data.MoviesProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites");

    static final String _ID = "_id";
    public static final String MOVIEID = "movieId";
    public static final String MOVIETITLE = "movieTitle";
    public static final String MOVIEYEAR = "movieYear";
    public static final String MOVIERATE = "movieRate";
    public static final String MOVIEDESCRIPTION = "movieDescription";
    public static final String MOVIEIMAGEADDRESS = "movieImageAddress";

    private static HashMap<String, String> MOVIES_PROJECTION_MAP;

    static final int MOVIE = 1;
    static final int MOVIE_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //Isto abaixo mira na tabela inteira
        uriMatcher.addURI(AUTHORITY, "favorites", MOVIE);
        //Isto abaixo é capaz de mirar numa única linha
        uriMatcher.addURI(AUTHORITY, "favorites/#", MOVIE_ID);
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Movies";
    static final String MOVIES_TABLE_NAME = "favorites";
    private static final String CREATE_DB_TABLE = " CREATE TABLE " + MOVIES_TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " movieId INTEGER NOT NULL," + " movieTitle TEXT NOT NULL, " + " movieYear TEXT NOT NULL," + " movieRate REAL NOT NULL," + " movieDescription TEXT NOT NULL," + " movieImageAddress TEXT NOT NULL);";

    protected static final class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  MOVIES_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = db.insert(	MOVIES_TABLE_NAME, "", values);

        if(rowID > 0){
            return ContentUris.withAppendedId(CONTENT_URI, rowID);
        }

        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(MOVIES_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case MOVIE:
                qb.setProjectionMap(MOVIES_PROJECTION_MAP);
                break;

            case MOVIE_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            sortOrder = MOVIEID;
        }

        Cursor c = qb.query(db,	projection,	selection, selectionArgs,null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case MOVIE:
                return db.delete(MOVIES_TABLE_NAME, selection, selectionArgs);

            case MOVIE_ID:
                selection = MoviesProvider.MOVIEID ;
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return db.delete( MOVIES_TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                count = db.update(MOVIES_TABLE_NAME, values, selection, selectionArgs);
                break;

            case MOVIE_ID:
                count = db.update(MOVIES_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? "AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case MOVIE:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case MOVIE_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
