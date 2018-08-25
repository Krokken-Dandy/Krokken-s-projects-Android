package com.example.krokken.storeinventory.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import com.example.krokken.storeinventory.R;
import com.example.krokken.storeinventory.data.InventoryContract.InventoryEntry;

public class InventoryProvider extends ContentProvider {

    InventoryDbHelper inventoryDbHelper;

    Resources res;

    // URI matcher code for the content URI for the inventory table
    private static final int INVENTORY = 100;

    // URI matcher code for the content URI for a single item in the inventory table
    private static final int INVENTORY_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, INVENTORY);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", INVENTORY_ID);
    }

    @Override
    public boolean onCreate() {
        inventoryDbHelper = new InventoryDbHelper(getContext());
        res = getContext().getResources();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrderBy) {
        SQLiteDatabase databaseReadable = inventoryDbHelper.getReadableDatabase();

        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                cursor = databaseReadable.query(InventoryEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrderBy);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = databaseReadable.query(InventoryEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrderBy);
                break;
            default:
                throw new IllegalArgumentException(
                        res.getString(R.string.IAE_query_query_not_supported) + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case INVENTORY_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException(
                        res.getString(R.string.IAE_get_type_unknown_uri) + uri +
                                res.getString(R.string.IAE_get_type_with_match) + match);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return insertInventoryItem(uri, contentValues);
            default:
                throw new IllegalArgumentException(
                        res.getString(R.string.IAE_insert_insertion_not_supported) + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase databaseWritable = inventoryDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case INVENTORY:
                rowsDeleted = databaseWritable.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = databaseWritable.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException(
                            res.getString(R.string.IAE_delete_delete_not_supported) + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues,
                      String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case INVENTORY:
                return updateInventory(uri, contentValues, selection, selectionArgs);
            case INVENTORY_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateInventory(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException(
                        res.getString(R.string.IAE_update_update_not_supported) + uri);
        }
    }

    // TODO finish this
    private Uri insertInventoryItem(Uri uri, ContentValues contentValues) {
        // Checks to make sure app has something to get before inserting
        String productName =
                contentValues.getAsString(InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
        if (productName == null) {
            throw new IllegalArgumentException(
                    res.getString(R.string.IAE_insert_inventory_item_product_name));
        }
        Integer productPrice =
                contentValues.getAsInteger(InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE);
        if (productPrice == null || productPrice < 0) {
            throw new IllegalArgumentException(
                    res.getString(R.string.IAE_insert_inventory_item_product_price));
        }
        Integer productQuantity =
                contentValues.getAsInteger(InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY);
        if (productQuantity == null || productQuantity < 0) {
            throw new IllegalArgumentException(
                    res.getString(R.string.IAE_insert_inventory_item_product_quantity));
        }
        String supplierName =
                contentValues.getAsString(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
        if (supplierName == null){
            throw new IllegalArgumentException(
                    res.getString(R.string.IAE_insert_inventory_item_supplier_name));
        }
        String supplierPhoneNumber =
                contentValues.getAsString(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);
        if (supplierPhoneNumber == null){
            throw new IllegalArgumentException(
                    res.getString(R.string.IAE_insert_inventory_item_supplier_phone_number));
        }

        SQLiteDatabase databaseWritable = inventoryDbHelper.getWritableDatabase();

        long id = databaseWritable.insert(InventoryEntry.TABLE_NAME, null, contentValues);

        if (id == -1) {
            Toast.makeText(getContext(), "Failed to insert new item", Toast.LENGTH_LONG).show();
            return null;
        }

        return ContentUris.withAppendedId(uri, id);
    }

    private int updateInventory(Uri uri, ContentValues contentValues,
                                String selection, String[] selectionArgs) {
        return 0;
    }
}
