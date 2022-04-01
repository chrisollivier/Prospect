package sio.nsi.prospect.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.model.User;

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

    private static final String key = "YourKey";
    private static final String salt = "YourSalt";
    private static final byte[] iv = new byte[16];

    private static Encryption encryption = Encryption.getDefault(key, salt, iv);

    public DataBaseHelper(@Nullable Context context) {
        super(context, "NSIProspect.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTableStatementUser = "create table user( Id INTEGER PRIMARY KEY AUTOINCREMENT,email text, password text, nom TEXT, prenom TEXT);";
        String CreateTableStatementProspect = "create table prospect(Id INTEGER primary key autoincrement,nom text,prenom text,siret text,raisonSociale text, score integer);";

        sqLiteDatabase.execSQL(CreateTableStatementUser);
        sqLiteDatabase.execSQL(CreateTableStatementProspect);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String CreateTableStatementDeleteUser = "Drop table if exists user;";
        String CreateTableStatementDeleteProspect = "Drop table if exists prospect;";

        sqLiteDatabase.execSQL(CreateTableStatementDeleteUser);
        sqLiteDatabase.execSQL(CreateTableStatementDeleteProspect);

        onCreate(sqLiteDatabase);
    }


    public void addNewUser(User user) {

        try {
            user.setEmail(encryption.encrypt(user.getEmail()));
            user.setPassword(encryption.encrypt(user.getPassword()));
            user.setNom(encryption.encrypt(user.getNom()));
            user.setPrenom(encryption.encrypt(user.getPrenom()));

            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put("email",user.getEmail());
            values.put("password",user.getPassword());
            values.put("nom", user.getNom());
            values.put("prenom", user.getPrenom());

            // after adding all values we are passing
            // content values to our table.
            sqLiteDatabase.insert("user", null, values);

            // at last we are closing our
            // database after adding database.
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // we have created a new method for reading all the courses.

    public ArrayList<User> readUser(User user) {
        // on below line we are creating a
        // database for reading our database.

        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{encryption.encryptOrNull(user.getEmail())});

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
                        encryption.decryptOrNull(cursorUser.getString(3)),
                        encryption.decryptOrNull(cursorUser.getString(4))
                ));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return userArrayList;
    }

    public void addNewProspect(Prospect prospect) {

        try {
            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();

            // on below line we are passing all values
            // along with its key and value pair.
            values.put("nom",prospect.getNom() );
            values.put("prenom",prospect.getPrenom() );
            values.put("siret",prospect.getSiret() );
            values.put("raisonSociale",prospect.getRaisonSociale() );
            values.put("score",prospect.getScore() );

            // after adding all values we are passing
            // content values to our table.
            sqLiteDatabase.insert("prospect", null, values);

            // at last we are closing our
            // database after adding database.
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Prospect> readAllProspect() {
        // on below line we are creating a
        // database for reading our database.

        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorProspect = db.rawQuery("SELECT * FROM prospect",new String[]{});

        // on below line we are creating a new array list.
        ArrayList<Prospect> prospectArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorProspect.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                prospectArrayList.add(new Prospect(
                        cursorProspect.getInt(0),
                        cursorProspect.getString(1),
                        cursorProspect.getString(2),
                        cursorProspect.getString(3),
                        cursorProspect.getString(4),
                        cursorProspect.getInt(5)
                ));
            } while (cursorProspect.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorProspect.close();
        return prospectArrayList;
    }

}
