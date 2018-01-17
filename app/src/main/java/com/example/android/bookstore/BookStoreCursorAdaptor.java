package com.example.android.bookstore;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookstore.data.BooksInfoContract.BooksStoreEntry;

/**
 * Created by qze713 on 1/15/18.
 */

public class BookStoreCursorAdaptor extends CursorAdapter {

    public BookStoreCursorAdaptor(Context context, Cursor c) {
        super(context, c, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView productTypeImageView = (ImageView) view.findViewById(R.id.imageid);
        TextView productNameTextView = (TextView) view.findViewById(R.id.productName);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        final TextView priceTextView = (TextView) view.findViewById(R.id.price);
        ImageView arrow = view.findViewById(R.id.sellimageid);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        int nameColumnIndex = cursor.getColumnIndex(BooksStoreEntry.COLUMN_PRODUCT_NAME);
        int productTypeColumnIndex = cursor.getColumnIndex(BooksStoreEntry.COLUMN_PRODUCT_TYPE);
        /*In this line below when you are trying to get the index from BooksStoreEntry, you have
        declared "price " instead of "price", there shouldn't be any space */
        int priceIndex = cursor.getColumnIndex(BooksStoreEntry.COLUMN_PRODUCT_PRICE);
        int quantityIndex = cursor.getColumnIndex(BooksStoreEntry.COLUMN_PRODUCT_QUANTITY);

        String productName = cursor.getString(nameColumnIndex);
        int price = cursor.getInt(priceIndex);
        int quantity = cursor.getInt(quantityIndex);
        int productType = cursor.getInt(productTypeColumnIndex);

        String sPrice = "$ " +  Integer.toString(price);

        switch (productType) {
            case 1:
                productTypeImageView.setImageResource(R.drawable.pen);
                break;

            case 2:
                productTypeImageView.setImageResource(R.drawable.books);
                break;

            case 3:
                productTypeImageView.setImageResource(R.drawable.pencil);
                break;

            case 4:
                productTypeImageView.setImageResource(R.drawable.bagpack);
                break;
            case 5:
                productTypeImageView.setImageResource(R.drawable.marker);
                break;

            default:
                productTypeImageView.setImageResource(R.drawable.unknown);


        }

        productNameTextView.setText(productName);
        //In the line below the text you are trying to set is an integer but the value expected is a string.
        priceTextView.setText(sPrice);
        quantityTextView.setText(Integer.toString(quantity));


    }
}
