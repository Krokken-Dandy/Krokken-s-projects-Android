package com.example.krokken.storeinventory.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import android.telephony.PhoneNumberUtils;

import java.text.NumberFormat;
import java.util.Locale;

public final class InventoryContract {

    private InventoryContract() {
    }

    public final static String CONTENT_AUTHORITY = "com.example.krokken.storeinventory";
    public final static String CONTENT_PREFIX = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_PREFIX + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "inventory";

    public static final class InventoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "inventory";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_INVENTORY_PRODUCT_NAME = "product_name";
        public final static String COLUMN_INVENTORY_PRODUCT_PRICE = "price";
        public final static String COLUMN_INVENTORY_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE = "shipping_fee";
        public final static String COLUMN_INVENTORY_PRODUCT_STOCK_TYPE = "stock_type";
        public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "phone_number";

        public final static int SHIPPING_BASE_COST = 0;
        public final static int SHIPPING_INT_FREE = 1;
        public final static int SHIPPING_LOCAL_FREE = 2;

        public final static int STOCK_SPECIAL_ORDER = 0;
        public final static int STOCK_CUSTOM_ORDER = 1;
        public final static int STOCK_REPLENISH_ORDER = 2;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;
    }


    public static String getFormattedPhoneNumber(String unformattedNumber) {
        String formattedNumber = PhoneNumberUtils.formatNumber(unformattedNumber);
        return formattedNumber;
    }

    public static String getFormattedPrice(String productPrice, Context context) {
        double price = (Double.parseDouble(productPrice) / 100);
        Locale currentLocale = context.getResources().getConfiguration().locale;
        String formattedPrice = NumberFormat.getNumberInstance(currentLocale).format(price);
        return formattedPrice;
    }
}
