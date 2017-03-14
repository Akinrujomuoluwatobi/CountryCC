package com.example.progtobi.countrycc;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class CountryCodeActivity extends AppCompatActivity {

    String[] countrynames;
    private Activity mActivity;
    // CountrySQLHelperAdapter db = new CountrySQLHelperAdapter(this);
    List<ModelClass> list;
    ModelClass selectedCountry;
    AutoCompleteTextView autoCompleteTextView;
    TextView txtView, countryView;

    class ItemAutoTextAdapter extends CursorAdapter
            implements android.widget.AdapterView.OnItemClickListener {

        private CountrySQLHelperAdapter mDbHelper;

        public ItemAutoTextAdapter(CountrySQLHelperAdapter dbHelper) {
            // Call the CursorAdapter constructor with a null Cursor.
            super(CountryCodeActivity.this, null);
            mDbHelper = dbHelper;
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            if (getFilterQueryProvider() != null) {
                return getFilterQueryProvider().runQuery(constraint);
            }

            Cursor cursor = mDbHelper.getMatchingStates(
                    (constraint != null ? constraint.toString() : null));

            return cursor;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view =
                    inflater.inflate(android.R.layout.simple_dropdown_item_1line,
                            parent, false);

            return view;
        }

        @Override
        public String convertToString(Cursor cursor) {
            final int columnIndex = cursor.getColumnIndexOrThrow("cname");
            final String str = cursor.getString(columnIndex);
            return str;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            final String text = convertToString(cursor);
            ((TextView) view).setText(text);

        }

        @Override
        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
            // Get the cursor, positioned to the corresponding row in the result set
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);

            // Get the state's capital from this row in the database.
            String country = cursor.getString(cursor.getColumnIndexOrThrow("code"));
            String countryname = cursor.getString(cursor.getColumnIndex("cname"));

            // Update the parent class's TextView
            txtView.setText(country);
            countryView.setText(countryname);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_code);
        CountrySQLHelperAdapter dbHelperAdapter = new CountrySQLHelperAdapter(this);

        //list = db.getAllFybs();


        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotxtview);
        txtView = (TextView) findViewById(R.id.displaytxt);
        countryView = (TextView) findViewById(R.id.contrydisplay);
        countrynames = getResources().getStringArray(R.array.country);

       /* db.onUpgrade(db.getWritableDatabase(), 1, 2);

        db.createFybs(new ModelClass("Nigeria", "234"));
        db.createFybs(new ModelClass("Afghanistan", "93"));
        db.createFybs(new ModelClass("Albania", "355"));
        db.createFybs(new ModelClass("Algeria", "213"));
        db.createFybs(new ModelClass("American Samoa", "1-684"));
        db.createFybs(new ModelClass("Andorra", "376"));
        db.createFybs(new ModelClass("Angola", "244"));
        db.createFybs(new ModelClass("Anguilla", "1-264"));
        db.createFybs(new ModelClass("Antarctica", "672"));
        db.createFybs(new ModelClass("Antigua and Barbuda", "1-268"));
        db.createFybs(new ModelClass("Argentina", "54"));
        db.createFybs(new ModelClass("Armenia", "374"));
        db.createFybs(new ModelClass("Aruba", "297"));
        db.createFybs(new ModelClass("Australia", "61"));
        db.createFybs(new ModelClass("Austria", "43"));
        db.createFybs(new ModelClass("Azerbaijan", "994"));
        db.createFybs(new ModelClass("Bahamas", "1-242"));
        db.createFybs(new ModelClass("Bahrain", "973"));
        db.createFybs(new ModelClass("Bangladesh", "880"));
        db.createFybs(new ModelClass("Barbados", "1-246"));
        db.createFybs(new ModelClass("Belarus", "375"));
        db.createFybs(new ModelClass("Belgium", "32"));
        db.createFybs(new ModelClass("Belize", "501"));
        db.createFybs(new ModelClass("Benin", "229"));
        db.createFybs(new ModelClass("Bermuda", "1-441"));
        db.createFybs(new ModelClass("Bhutan", "975"));
        db.createFybs(new ModelClass("Bolivia", "591"));
        db.createFybs(new ModelClass("Bosnia and Herzegovina", "387"));
        db.createFybs(new ModelClass("Botswana", "267"));
        db.createFybs(new ModelClass("Brazil", "55"));
        db.createFybs(new ModelClass("", ""));
        db.createFybs(new ModelClass("", ""));


        list = db.getAllFybs();
*/
        ItemAutoTextAdapter adapter = this.new ItemAutoTextAdapter(dbHelperAdapter);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(adapter);

/*

        final List<String> listName = new ArrayList<String>();
        final List<String> listCode = new ArrayList<String>();


        for (int i = 0; i < list.size(); i++) {
            listName.add(i, list.get(i).getCountryName());
            listCode.add(i, list.get(i).getCallCode());


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName);
            autoCompleteTextView.setAdapter(adapter);
            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                    //String code = cursor.getString(cursor.getColumnIndexOrThrow("code"));
                    txtView.setText(list.get(position).getCallCode());

                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get all books again, because something changed
        list = db.getAllFybs();
        List<String> listName = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listName.add(i, list.get(i).getCountryName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName);
        autoCompleteTextView.setAdapter(adapter);
    }
    */


    }
}

