package com.example.progtobi.countrycc;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by PROG. TOBI on 14-Jun-16.
 */
public class CountrySQLHelperAdapter {
    public static final int version = 1;
    public static final String db_NAME = "countrycc";
    public static final String db_TABLE = "country";
    public static final String country_NAME = "cname";
    public static final String country_CODE = "code";
    //private static final String[] COLUMNS = {country_ID, country_NAME, country_CODE};

    private static final String[][] countries = {
            { "Nigeria", "234" }, { "Afghanistan", "93",}, { "Albania", "355"},
            { "Algeria", "213" }, { "American Samoa", "1-684" }, { "Andorra", "376" }, { "Angola", "244" },
            { "Anguilla", "1-264" }, { "Antarctica", "672" }, { "Antigua and Barbuda", "1-268" }, { "Argentina", "54" },
            { "Armenia", "374"}, { "Aruba", "297" }, { "Australia", "61"}, { "Austria", "43" },
            { "Azerbaijan", "994"}, {"Bahamas", "1-242" }, {"Bahrain", "973"}, { "Bangladesh", "880"},
            { "Barbados", "1-246" }, { "Belarus", "375"}, { "Belgium", "32"}, { "Belize", "501"},
            { "Benin", "229" }, { "Bermuda", "1-441"}, {"Bhutan", "975"},
            { "Bolivia", "591"}, { "Bosnia and Herzegovina", "387"}, { "Botswana", "267"},
            { "Brazil", "55" }, { "British Indian Ocean Territory", "(246)" },
            { "British Virgin Islands", "(1-284)" }, { "Brunei", "(673)" },
            { "Bulgaria", "(359)" }, { "Burkina Faso", "(226)" }, { "Burundi", "(257)" },
            { "Cambodia", "(855)" }, { "Cameroon", "(237)" }, { "Canada", "(1)" }, { "Cape Verde", "(238)" },
            { "Chad", "(235)" }, { "Chile", "56" }, { "China", "86" }, { "Christmas Island", "61" },
            { "Cocos Islands", "61" }, { "Colombia", "57" }, { "Comoros", "269" }, { "Cook Islands", "682" },
            { "Costa Rica", "506" }, { "Croatia", "385" }, { "Cuba", "53" }, { "Curacao", "599" },
            { "Cyprus", "357" }, { "Czech Republic", "420" }, { "Democratic Republic of the Congo", "243" },
            { "Denmark", "45" },{ "Djibouti", "253" }, { "Dominica", "1-767" }, { "Dominican Republic", "1-809, 1-829, 1-849" },
            { "East Timor", "670" }, { "Ecuador", "593" },{ "Egypt", "20" },
            { "El Salvador", "503" }, { "Equatorial Guinea", "240" }, { "Eritrea", "291" }, { "Estonia", "372" },
            { "Ethiopia", "251" }, { "Falkland Islands", "500" }, { "Faroe Islands", "298" }, { "Fiji", "679" },
            { "Finland", "358" }, { "France", "33" }, { "French Polynesia", "689" }, { "Gabon", "241" },
            { "Gambia", "220" },{ "Georgia", "995" }, { "Germany", "49" }, { "Ghana", "233" }, { "Gibraltar", "350" },
            { "Greece", "30" },{ "Greenland", "299" }, { "Grenada", "1-473" }, { "Guam", "1-671" }, { "Guatemala", "502" },
            { "Guernsey", "44-1481" }, { "Guinea", "224" }, { "Guinea-Bissau", "245" }, { "Guyana", "592" },
            { "Haiti", "509" }, { "Honduras", "504" }, { "Hong Kong", "852" }, { "Hungary", "36" },
            { "Iceland", "354" }, { "India", "91" }, { "Indonesia", "62" }, { "Iran", "98" }, { "Iraq", "964" },
            { "Ireland", "353" }, { "Isle of Man", "44-1624" }, { "Israel", "972" }, { "Italy", "39" },
            { "Ivory Coast", "225" }, { "Jamaica", "1-876" }, { "Japan", "81" }, { "Jersey", "44-1534" }, { "Kazakhstan", "7" },
            { "Kenya", "254" }, { "Kiribati", "686" },{ "Kosovo", "383" }, { "Kuwait", "965" }, { "Kyrgyzstan", "996" },
            { "Laos", "856" }, { "Latvia", "371" }, { "Lebanon", "961" }, { "Lesotho", "266" }, { "Liberia", "231" },
            { "Libya", "218" }, { "Liechtenstein", "423" }, { "Lithuania", "370" },{ "Luxembourg", "352" },
            { "Macao", "853" }, { "Macedonia", "389" }, { "Madagascar", "261" }, { "Malawi", "265" }, { "Malaysia", "60" },
            { "Maldives", "960" }, { "Mali", "223" }, { "Malta", "356" }, { "Marshall Islands", "692" }, { "Mauritania", "222" },
            { "Mauritius", "230" }, { "Mayotte", "262" }, { "Mexico", "52" }, { "Micronesia", "691" },{ "Moldova", "373" },
            { "Monaco", "377" }, { "Mongolia", "976" }, { "Montenegro", "382" }, { "Montserrat", "1-664" },
            { "Morocco", "212" }, { "Mozambique", "258" }, { "Myanmar", "95" }, { "Namibia", "264" }, { "Nauru", "674" },
            { "Nepal", "977" }, { "Netherlands", "31" }, { "Netherlands Antilles", "599" }, { "New Caledonia", "687" },
            { "New Zealand", "64" },{ "Nicaragua", "505" }, { "Niger", "227" },{ "Nigeria", "234" }, { "North Korea", "850" },
            { "Northern Mariana Islands", "1-670" }, { "Norway", "47" },{ "Oman", "968" }, { "Pakistan", "92" },
            { "Palau", "680" }, { "Palestine", "970" }, { "Panama", "507" }, { "Papua New Guinea", "675" },
            { "Paraguay", "595" },{ "Peru", "51" }, { "Philippines", "63" }, { "Pitcairn", "64" },
            { "Poland", "48" },{ "Portugal", "351" }, { "Puerto Rico", "1-787, 1-939" },{ "Republic of the Congo", "242" }, { "Reunion", "262" },
            { "Romania", "40" }, { "Russia", "7" }, { "Rwanda", "250" }, { "Saint Barthelemy", "590" },
            { "Saint Helena", "290" }, { "Saint Kitts and Nevis", "1-869" }, { "Saint Lucia", "1-758" },
            { "Saint Martin", "590" }, { "Saint Pierre and Miquelon", "508" }, { "Saint Vincent and the Grenadines", "1-784" },
            { "Samoa", "685" }, { "San Marino", "378" }, { "Sao Tome and Principe", "239" },{ "Saudi Arabia", "966" },
            { "Senegal", "221" },{ "Serbia", "381" }, { "Seychelles", "248" }, { "Sierra Leone", "232" },{ "Singapore", "65" },
            { "Sint Maarten", "1-721" }, { "Slovakia", "421" }, { "Slovenia", "386" }, { "Solomon Islands", "677" },
            { "Somalia", "252" }, { "South Africa", "27" }, { "South Korea", "82" }, { "South Sudan", "211" },
            { "Spain", "34" }, { "Sri Lanka", "94" }, { "Sudan", "249" },{ "Suriname", "597" }, { "Svalbard and Jan Mayen", "47" },
            { "Swaziland", "268" },{ "Sweden", "46" }, { "Switzerland", "41" }, { "Syria", "963" },{ "Taiwan", "886" },
            { "Tajikistan", "992" },{ "Tanzania", "255" }, { "Thailand", "66" }, { "Togo", "228" }, { "Tokelau", "690" },
            { "Tonga", "676" }, { "Trinidad and Tobago", "1-868" }, { "Tunisia", "216" }, { "Turkey", "90" },
            { "Turkmenistan", "993" }, { "Turks and Caicos Islands", "1-649" }, { "Tuvalu", "688" }, { "U.S. Virgin Islands", "1-340" },
            { "Uganda", "256" }, { "Ukraine", "380" }, { "United Arab Emirates", "971" }, { "United Kingdom", "44" },
            { "United States", "1" }, { "Uruguay", "598" }, { "Uzbekistan", "998" }, { "Vanuatu", "678" }, { "Vatican", "379" },
            { "Venezuela", "58" }, { "Vietnam", "84" }, { "Wallis and Futuna", "681" }, { "Western Sahara", "212" },
            { "Yemen", "967" },{ "Zambia", "260" }, { "Zimbabwe", "263" }


    };


    private class CountrySQLHelper extends SQLiteOpenHelper {
        public CountrySQLHelper(Context context) {
            super(context, db_NAME, null, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_COUNTRY_TABLE = "create table " + db_TABLE
                    + "(_id integer primary key autoincrement"
                    + ", cname text not null"
                    + ", code text not null)";
            db.execSQL(CREATE_COUNTRY_TABLE);
            populateWithData(db);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS country");
            this.onCreate(db);


        }

        public void createFybs(ModelClass model_class) {
            // get reference of the fybDB database

            SQLiteDatabase db = this.getWritableDatabase();

            // make values to be inserted

            ContentValues values = new ContentValues();
            values.put(country_NAME, model_class.getCountryName());
            values.put(country_CODE, model_class.getCallCode());

            // insert book
            db.insert(db_TABLE, null, values);

            // close database transaction
            db.close();
        }


        /*
        public ModelClass readModel_class(int id) {
            // get reference of the fybDB database
            SQLiteDatabase db = this.getReadableDatabase();

            // get fyb query
            Cursor cursor = db.query(db_TABLE, // a. table
                    COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

            // if results !=null, parse the first one
            if (cursor != null)
                cursor.moveToFirst();

            ModelClass model_class = new ModelClass();
            model_class.setId(Integer.parseInt(cursor.getString(0)));
            model_class.setCountryName(cursor.getString(1));
            model_class.setCallCode(cursor.getString(2));

            return model_class;
        }

        */

        public List<ModelClass> getAllFybs() {
            List<ModelClass> fybs = new LinkedList<ModelClass>();
            // select student query
            String query = "SELECT  * FROM " + db_TABLE;
            // get reference of the BookDB database
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            // parse all results
            ModelClass model_class = null;
            if (cursor.moveToFirst()) {
                do {
                    model_class = new ModelClass();
                    model_class.setId(Integer.parseInt(cursor.getString(0)));
                    model_class.setCountryName(cursor.getString(1));
                    model_class.setCallCode(cursor.getString(2));

                    fybs.add(model_class);
                } while (cursor.moveToNext());
            }
            return fybs;
        }
    }

    private void populateWithData(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            // Populate the database with the state/capital city
            // pairs found in the States array.
            for (String[] s : countries) {
                values.put("cname", s[0]);
                values.put("code", s[1]);

                db.insert(db_TABLE, "country", values);            }
            db.setTransactionSuccessful();
            Log.e("DATABASE INSERTION", "INSERTED");
        }
        finally {
            db.endTransaction();
        }


    }


    private CountrySQLHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Activity mActivity;

    public CountrySQLHelperAdapter(Activity activity) {
        this.mActivity = activity;
        mDbHelper = this.new CountrySQLHelper(activity);
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }


    public Cursor getMatchingStates(String constraint) throws SQLException {

        String queryString =
                "SELECT _id, cname, code FROM " + db_TABLE;

        if (constraint != null) {
            // Query for any rows where the state name begins with the
            // string specified in constraint.
            //
            // NOTE:
            // If wildcards are to be used in a rawQuery, they must appear
            // in the query parameters, and not in the query string proper.
            // See http://code.google.com/p/android/issues/detail?id=3153
            constraint = constraint.trim() + "%";
            queryString += " WHERE cname LIKE ?";
        }
        String params[] = {constraint};

        if (constraint == null) {
            // If no parameters are used in the query,
            // the params arg must be null.
            params = null;
        }
        try {
            Cursor cursor = mDb.rawQuery(queryString, params);
            if (cursor != null) {
                this.mActivity.startManagingCursor(cursor);
                cursor.moveToFirst();
                return cursor;
            }
        } catch (SQLException e) {
            Log.e("AutoCompleteDbAdapter", e.toString());
            throw e;
        }

        return null;
    }
}
