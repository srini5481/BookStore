package com.example.android.bookstore;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstore.data.BooksInfoContract;
import com.example.android.bookstore.data.BooksStoreHelper;
import com.example.android.bookstore.data.BooksInfoContract.BooksStoreEntry;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private BooksStoreHelper bookStoreDbHelper;

    private static final int INVENTORY_LOADER = 0;


    private BookStoreCursorAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookStoreDbHelper = new BooksStoreHelper(this);


        ListView listView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_view);

        listView.setEmptyView(emptyView);

         adapter = new BookStoreCursorAdaptor(this, null);

        listView.setAdapter(adapter);

        // Setup the item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);

                Uri currentProductUri = ContentUris.withAppendedId(BooksStoreEntry.CONTENT_URI, id);
                intent.setData(currentProductUri);
                startActivity(intent);

            }
        });

        // Kick off the loader
        getLoaderManager().initLoader(INVENTORY_LOADER, null, this);


    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }*/

    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        //SQLiteDatabase db = bookStoreDbHelper.getReadableDatabase();
        String[] project = {
                BooksStoreEntry._ID,
                BooksStoreEntry.COLUMN_PRODUCT_NAME,
                BooksStoreEntry.COLUMN_PRODUCT_TYPE,
                BooksStoreEntry.COLUMN_PRODUCT_PRICE,
                BooksStoreEntry.COLUMN_PRODUCT_QUANTITY,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = getContentResolver().query(
                BooksStoreEntry.CONTENT_URI,
                project,
                null,
                null,
                null);


        ListView listView = (ListView) findViewById(R.id.list);

        BookStoreCursorAdaptor adapter = new BookStoreCursorAdaptor(this, cursor);

        listView.setAdapter(adapter);



    }


    private void insertPetData(){

        SQLiteDatabase db = bookStoreDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BooksStoreEntry.COLUMN_PRODUCT_NAME, "product_boaards");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_TYPE, "1");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_PRICE, "25");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_QUANTITY,"250");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME,"Book_board1");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL,"email@board1_Supplier1.com");
        values.put(BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER,"3025555555");
        //long newRowId = db.insert(BooksStoreEntry.TABLE_NAME, null, values);

        Uri newUri = getContentResolver().insert(BooksStoreEntry.CONTENT_URI, values);

        if (newUri != null){
            //Toast.makeText(getApplicationContext(), (int) newRowId,Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Product Stores into table with Row id: " + newUri, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error in inserting Data",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_book_info:
                Toast.makeText(this,"inserting Book info into table",Toast.LENGTH_SHORT).show();
                insertPetData();
                //displayDatabaseInfo();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] project = {
                BooksStoreEntry._ID,
                BooksStoreEntry.COLUMN_PRODUCT_NAME,
                BooksStoreEntry.COLUMN_PRODUCT_TYPE,
                BooksStoreEntry.COLUMN_PRODUCT_PRICE,
                BooksStoreEntry.COLUMN_PRODUCT_QUANTITY,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_NAME,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL,
                BooksStoreEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER
        };
        return new CursorLoader(this,   // Parent activity context
                BooksStoreEntry.CONTENT_URI,   // Provider content URI to query
                project,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }
}
