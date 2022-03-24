package com.SIO.Questionner.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.SIO.Questionner.model.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private static final String DB_NAME = "userQuestioner.db";      //variable is for our database name.
    private static final int DB_VERSION = 9;     //int is our database version
    private static final String TABLE_NAME = "user_question";     //variable is for our table name.
    private static final String ID_COL = "ID_user";     //variable is for our id column.
    private static final String NOM_COL = "nom";     //variable is for our name column
    private static final String PRENOM_COL = "prenom";      //variable is for our last name column
    private static final String SCORE_COL = "bestscore";    //variable is for our last score column
    private static final String key = "YourKey";      //variable is for our privet key
    private static final String salt = "YourSalt";      //variable is for our Salt
    private static byte[] iv = new byte[16];

    private static Encryption encryption = Encryption.getDefault(key, salt, iv);

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTableStatement = "create table user_question( ID_user INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, prenom TEXT, bestscore INTEGER);";
        sqLiteDatabase.execSQL(CreateTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String CreateTableStatement = "Drop table if exists user_question;";
        sqLiteDatabase.execSQL(CreateTableStatement);
        onCreate(sqLiteDatabase);
    }

    public void updateUser(String userName,int bestscore) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(SCORE_COL, bestscore);

        sqLiteDatabase.update(TABLE_NAME, values, "nom = ?", new String[]{encryption.encryptOrNull(userName)});
        sqLiteDatabase.close();
    }

    public void addNewCourse(String nom, String prenom, int score) {

        try {
            String nomEncrypted = encryption.encrypt(nom);
            String prenomEncrypted = encryption.encrypt(prenom);

            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put(NOM_COL, nomEncrypted);
            values.put(PRENOM_COL, prenomEncrypted);
            values.put(SCORE_COL, score);

            // after adding all values we are passing
            // content values to our table.
            sqLiteDatabase.insert(TABLE_NAME, null, values);

            // at last we are closing our
            // database after adding database.
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // we have created a new method for reading all the courses.

    public ArrayList<User> readUser(String userName) {
        // on below line we are creating a
        // database for reading our database.

        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM user_question WHERE nom = ?" , new String[] {encryption.encryptOrNull(userName)});

        // on below line we are creating a new array list.
        ArrayList<User> userArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userArrayList.add(new User(
                        cursorUser.getInt(0),
                        encryption.decryptOrNull(cursorUser.getString(1)),
                        encryption.decryptOrNull(cursorUser.getString(2)),
                        cursorUser.getInt(3)
                ));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return userArrayList;


    }
}
