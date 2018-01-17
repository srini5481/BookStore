package com.example.android.bookstore.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by qze713 on 1/14/18.
 */

public class BooksInfoContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.bookstore";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCT = "product";


    public static abstract class BooksStoreEntry implements BaseColumns {


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCT);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCT;

        public static final String TABLE_NAME = "product_new";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "productName";
        public static final String COLUMN_PRODUCT_TYPE = "productType";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_PRODUCT_SUPPLIER_EMAIL = "supplierEmail";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";


        public static final int PRODUCT_TYPE_PEN = 1;
        public static final int PRODUCT_TYPE_BOOK = 2;
        public static final int PRODUCT_TYPE_PENCILBOX = 3;
        public static final int PRODUCT_TYPE_BAGPACK = 4;
        public static final int PRODUCT_TYPE_MARKER = 5;
        public static final int PRODUCT_TYPE_UNKNOWN = 0;

    }
}
