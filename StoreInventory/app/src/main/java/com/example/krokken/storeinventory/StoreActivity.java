package com.example.krokken.storeinventory;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krokken.storeinventory.data.InventoryContract;
import com.example.krokken.storeinventory.data.InventoryContract.InventoryEntry;
import com.example.krokken.storeinventory.data.InventoryDbHelper;

public class StoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int INVENTORY_LOADER = 0;

    private static final int CAMERA_PERMISSION_CODE = 200;

    private static final int DUMMY_PRODUCT_QUANTITY = 5;

    private static final int DUMMY_PRODUCT_SHIPPING_FEE = 2;

    private static final int DUMMY_PRODUCT_STOCK_TYPE = 1;

    InventoryDbHelper mDbHelper;

    View emptyView;

    FloatingActionButton fabAddItem;

    InventoryCursorAdapter mInventoryCursorAdapter;

    ListView inventoryListView;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        onCreateClickListeners();

        inventoryListView.setEmptyView(emptyView);
        inventoryListView.setAdapter(mInventoryCursorAdapter);

        getLoaderManager().initLoader(INVENTORY_LOADER, null, StoreActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_edit_text_info:
                insertDummyData();
                return true;
            case R.id.action_delete_all_items:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeVariables() {
        mDbHelper = new InventoryDbHelper(this);
        fabAddItem = findViewById(R.id.fab_add_item);
        emptyView = findViewById(R.id.empty_view);
        inventoryListView = findViewById(R.id.list);
        mInventoryCursorAdapter = new InventoryCursorAdapter(this, null);
    }

    private void onCreateClickListeners() {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri currentItemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, id);
                inventoryPopup(view, currentItemUri);
            }
        });
    }

    private void insertDummyData() {
        ContentValues values = new ContentValues();
        values.put(
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME,
                getString(R.string.dummy_product_name));
        values.put(
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE,
                getString(R.string.dummy_product_price));
        values.put(
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY,
                DUMMY_PRODUCT_QUANTITY);
        values.put(
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE,
                DUMMY_PRODUCT_SHIPPING_FEE);
        values.put(
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE,
                DUMMY_PRODUCT_STOCK_TYPE);
        values.put(
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                getString(R.string.dummy_supplier_name));
        values.put(
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER,
                getString(R.string.dummy_supplier_phone_number));

        Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(this, R.string.dummy_toast_failed_insert, Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_delete_question);
        builder.setPositiveButton(R.string.delete_dialog_delete_all, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAll();
            }
        });
        builder.setNegativeButton(R.string.delete_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAll() {
        int rowsDeleted = getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
        Toast.makeText(this, rowsDeleted + getString(R.string.delete_all_toast_rows_deleted), Toast.LENGTH_LONG).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER
        };

        return new CursorLoader(this,
                InventoryEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mInventoryCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mInventoryCursorAdapter.swapCursor(null);
    }

    private void inventoryPopup(View anchorView, final Uri currentItemUri) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE,
                InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER
        };

        Cursor currentItemCursor =
                getContentResolver().query(
                        currentItemUri,
                        projection,
                        null,
                        null,
                        null);

        currentItemCursor.moveToFirst();

        int currentProductNameColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
        int currentProductPriceColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE);
        int currentProductQuantityColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY);
        int currentProductShippingFeeColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE);
        int currentProductStockTypeColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE);
        int currentSupplierNameColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
        int currentSupplierPhoneNumberColumnIndex =
                currentItemCursor.getColumnIndex(
                        InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

        String currentProductNameString =
                currentItemCursor.getString(currentProductNameColumnIndex);
        String currentProductPriceString =
                InventoryContract.getFormattedPrice(
                        currentItemCursor.getString(currentProductPriceColumnIndex), this);
        int currentProductQuantityInt =
                currentItemCursor.getInt(currentProductQuantityColumnIndex);
        int currentProductShippingFeeInt =
                currentItemCursor.getInt(currentProductShippingFeeColumnIndex);
        int currentProductStockTypeInt =
                currentItemCursor.getInt(currentProductStockTypeColumnIndex);
        String currentSupplierNameString =
                currentItemCursor.getString(currentSupplierNameColumnIndex);
        String currentSupplierPhoneNumberString =
                InventoryContract.getFormattedPhoneNumber(
                        currentItemCursor.getString(currentSupplierPhoneNumberColumnIndex));

        View popupView = getLayoutInflater().inflate(R.layout.popup_inventory_item, null);

        popupWindow = new PopupWindow(popupView,
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        ImageView productImage = popupView.findViewById(R.id.popup_item_image);
        TextView productName = popupView.findViewById(R.id.popup_product_name);
        TextView productPrice = popupView.findViewById(R.id.popup_product_price);
        TextView productQuantity = popupView.findViewById(R.id.popup_product_quantity);
        TextView productShippingFee = popupView.findViewById(R.id.popup_product_shipping_fee);
        TextView productStockType = popupView.findViewById(R.id.popup_product_stock_type);
        TextView supplierName = popupView.findViewById(R.id.popup_supplier_name);
        TextView supplierPhoneNumber = popupView.findViewById(R.id.popup_supplier_phone_number);
        View popupFrame = popupView.findViewById(R.id.popup_frame);
        popupFrame.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        StoreActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(StoreActivity.this, "Camera no bueno", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(StoreActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                } else {
                    Toast.makeText(StoreActivity.this, "Camera es Bueno", Toast.LENGTH_SHORT).show();
                }
            }
        });

        productName.setText(currentProductNameString);
        productPrice.setText(currentProductPriceString);
        productQuantity.setText("" + currentProductQuantityInt);
        productShippingFee.setText("" + currentProductShippingFeeInt);
        productStockType.setText("" + currentProductStockTypeInt);
        supplierName.setText(currentSupplierNameString);
        supplierPhoneNumber.setText(currentSupplierPhoneNumberString);

        //Dismiss popup window when clicked outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.setAnimationStyle(R.style.AnimationPopup);
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

        // TODO Map this to an edit button inside popup
        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StoreActivity.this, EditorActivity.class);
                intent.setData(currentItemUri);
                startActivity(intent);
            }
        });
        currentItemCursor.close();
    }
}
