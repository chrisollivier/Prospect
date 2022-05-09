package sio.nsi.prospect.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.model.User;

import java.io.IOException;
import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String key = "YourKey";
    private static final String salt = "YourSalt";
    private static final byte[] iv = new byte[16];
    private static int userResult = 0;
    private static final Encryption encryption = Encryption.getDefault(key, salt, iv);

    public DataBaseHelper(@Nullable Context context) {
        super(context, "NSIProspect.db", null, 14);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CreateTableStatementUser = "create table user( Id INTEGER PRIMARY KEY AUTOINCREMENT,email text, password text, nom TEXT, prenom TEXT);";
        String CreateTableStatementProspect = "create table prospect(Id INTEGER primary key autoincrement,nom text,prenom text,siret text,raisonSociale text, score integer, mail text,tel text);";

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


    /*
     * Ajout d'un utilisateur
     * @param user
     */
    public void addNewUser(User user) {
        try {
            assert encryption != null;
            user.setEmail(encryption.encrypt(user.getEmail()));
            user.setPassword(encryption.encrypt(user.getPassword()));
            user.setNom(encryption.encrypt(user.getNom()));
            user.setPrenom(encryption.encrypt(user.getPrenom()));

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("email",user.getEmail());
            values.put("password",user.getPassword());
            values.put("nom", user.getNom());
            values.put("prenom", user.getPrenom());

            sqLiteDatabase.insert("user", null, values);

            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Lecture de tous les utilisateurs
     * @return ArrayList<User>
     */
    public ArrayList<User> readAllUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        assert encryption != null;
        Cursor cursorUser = db.rawQuery("SELECT * FROM user", new String[]{});

        ArrayList<User> userArrayList = new ArrayList<>();
        if (cursorUser.moveToFirst()) {
            do {
                userArrayList.add(new User(
                        cursorUser.getInt(0),
                        encryption.decryptOrNull(cursorUser.getString(1)),  //MAIL
                        encryption.decryptOrNull(cursorUser.getString(2)),  //PASSWORD
                        encryption.decryptOrNull(cursorUser.getString(3)),  //NOM
                        encryption.decryptOrNull(cursorUser.getString(4))   //PRENOM
                ));
            } while (cursorUser.moveToNext());
        }
        cursorUser.close();
        return userArrayList;
    }

    /*
     * Lecture des data d'un utilisateur
     * @param user
     * @return ArrayList<User>
    */
    public ArrayList<User> readUserFormUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        assert encryption != null;
        Cursor cursorUser = db.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{encryption.encryptOrNull(user.getEmail())});

        ArrayList<User> userArrayList = new ArrayList<>();
        if (cursorUser.moveToFirst()) {
            do {
                userArrayList.add(new User(
                        cursorUser.getInt(0),
                        encryption.decryptOrNull(cursorUser.getString(1)),  //MAIL
                        encryption.decryptOrNull(cursorUser.getString(2)),  //PASSWORD
                        encryption.decryptOrNull(cursorUser.getString(3)),  //NOM
                        encryption.decryptOrNull(cursorUser.getString(4))   //PRENOM
                ));
            } while (cursorUser.moveToNext());
        }
        cursorUser.close();
        return userArrayList;
    }

    /*
     * Check si un utilisateur existe
     * @param String email
     * @return int
    */
    public int readNumberUserFromMail(String mail) {
        SQLiteDatabase db = this.getReadableDatabase();
        userResult = 0;
        assert encryption != null;
        Cursor cursorUser = db.rawQuery("SELECT * FROM user WHERE email = ?", new String[]{encryption.encryptOrNull(mail)});

        if (cursorUser.moveToFirst()) {
            do {
                userResult++;
            } while (cursorUser.moveToNext());
        }
        cursorUser.close();
        return userResult;
    }

    /*
     * Ajout d'un prospect
     * @param prospect
    */
    public void addNewProspect(Prospect prospect) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("nom",prospect.getNom());
            values.put("prenom",prospect.getPrenom());
            values.put("siret",prospect.getSiret());
            values.put("raisonSociale",prospect.getRaisonSociale());
            values.put("score",prospect.getScore());
            values.put("mail",prospect.getMail());
            values.put("tel",prospect.getTel());

            sqLiteDatabase.insert("prospect", null, values);
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Lecture de tous les prospects
     * @return ArrayList<Prospect>
     */
    public ArrayList<Prospect> readAllProspect() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorProspect = db.rawQuery("SELECT * FROM prospect",new String[]{});
        ArrayList<Prospect> prospectArrayList = new ArrayList<>();
        if (cursorProspect.moveToFirst()) {
            do {
                prospectArrayList.add(new Prospect(
                        cursorProspect.getInt(0),       //ID
                        cursorProspect.getString(1),    //nom
                        cursorProspect.getString(2),    //prenom
                        cursorProspect.getString(3),    //siret
                        cursorProspect.getString(4),    //RS
                        cursorProspect.getInt(5),       //score
                        cursorProspect.getString(6),    //tel
                        cursorProspect.getString(7)     //mail
                ));
            } while (cursorProspect.moveToNext());
        }
        cursorProspect.close();
        return prospectArrayList;
    }

    /*
    * Check si un prospect existe depuis son nom, prénom et Siret
    * @param String nom, String prenom, String siret
    * @return int
     */
    public int readNumberProspectFromNomPrenomSiret(String nom ,String prenom ,String siret) {
        SQLiteDatabase db = this.getReadableDatabase();
        userResult = 0;
        Cursor cursorUser = db.rawQuery("SELECT * FROM prospect WHERE nom = ? And prenom = ? And siret = ?", new String[]{nom,prenom,siret});

        if (cursorUser.moveToFirst()) {
            do {
                userResult++;
            } while (cursorUser.moveToNext());
        }
        cursorUser.close();
        return userResult;
    }
}
