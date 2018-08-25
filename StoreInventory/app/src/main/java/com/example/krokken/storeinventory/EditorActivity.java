package com.example.krokken.storeinventory;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.krokken.storeinventory.data.InventoryContract.InventoryEntry;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mProductNameEditText;
    private EditText mProductPriceEditText;
    private EditText mProductQuantityEditText;
    private EditText mSupplierNameEditText;
    private EditText mSupplierPhoneNumberEditText;
    private Spinner mShippingSpinner;
    private Spinner mStockSpinner;

    private boolean mItemHasChanged = false;

    private Uri mCurrentItemUri;

    private int mShippingFee;
    private int mStockType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentItemUri = intent.getData();

        if (mCurrentItemUri == null) {
            setTitle("Add an Item");
            invalidateOptionsMenu();
        } else {
            setTitle("Edit an Item");
            getLoaderManager().initLoader(0, null, this);
        }

        mProductNameEditText = findViewById(R.id.product_name);
        mProductPriceEditText = findViewById(R.id.product_price);
        mProductQuantityEditText = findViewById(R.id.product_quantity);
        mSupplierNameEditText = findViewById(R.id.supplier_name);
        mSupplierPhoneNumberEditText = findViewById(R.id.supplier_phone_number);
        mShippingSpinner = (Spinner) findViewById(R.id.spinner_shipping);
        mStockSpinner = (Spinner) findViewById(R.id.spinner_stock);

        setupSpinners();
        setupFloatingLabelErrors();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentItemUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_edit_text_info:
                saveItem();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mItemHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!mItemHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the editor activity.
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
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

        // Create and/or open a database to read from it
        return new CursorLoader(this,
                mCurrentItemUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
            int productPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE);
            int productQuantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY);
            int productShippingFeeColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE);
            int productStockTypeColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

            String productNameString = cursor.getString(productNameColumnIndex);
            String productPriceString = cursor.getString(productPriceColumnIndex);
            int productQuantityInt = cursor.getInt(productQuantityColumnIndex);
            int productShippingFeeInt = cursor.getInt(productShippingFeeColumnIndex);
            int productStockTypeInt = cursor.getInt(productStockTypeColumnIndex);
            String supplierNameString = cursor.getString(supplierNameColumnIndex);
            String supplierPhoneNumberString = cursor.getString(supplierPhoneNumberColumnIndex);

            mProductNameEditText.setText(productNameString);
            mProductPriceEditText.setText(productPriceString);
            mProductQuantityEditText.setText(productQuantityInt);
            mSupplierNameEditText.setText(supplierNameString);
            mSupplierPhoneNumberEditText.setText(supplierPhoneNumberString);

            switch (productShippingFeeInt) {
                case InventoryEntry.SHIPPING_INT_FREE:
                    mShippingSpinner.setSelection(1);
                    break;
                case InventoryEntry.SHIPPING_LOCAL_FREE:
                    mShippingSpinner.setSelection(2);
                    break;
                default:
                    mShippingSpinner.setSelection(0); // SHIPPING_BASE_COST
                    break;
            }

            switch (productStockTypeInt) {
                case InventoryEntry.STOCK_CUSTOM_ORDER:
                    mStockSpinner.setSelection(1);
                    break;
                case InventoryEntry.STOCK_REPLENISH_ORDER:
                    mShippingSpinner.setSelection(2);
                    break;
                default:
                    mStockSpinner.setSelection(0); // STOCK_SPECIAL_ORDER
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mProductNameEditText.setText("");
        mProductPriceEditText.setText("");
        mProductQuantityEditText.setText("");
        mSupplierNameEditText.setText("");
        mSupplierPhoneNumberEditText.setText("");
        mShippingSpinner.setSelection(0); // Base Shipping Fee
        mStockSpinner.setSelection(0); // Special order
    }

    private void saveItem() {
        String productNameString = mProductNameEditText.getText().toString().trim();
        String productPriceString = mProductPriceEditText.getText().toString().trim();
        String productQuantityString = mProductQuantityEditText.getText().toString().trim();
        String supplierNameString = mSupplierNameEditText.getText().toString().trim();
        String supplierPhoneNumberString = mSupplierPhoneNumberEditText.getText().toString().trim();

        if (mCurrentItemUri == null &&
                TextUtils.isEmpty(productNameString) &&
                TextUtils.isEmpty(productPriceString) &&
                TextUtils.isEmpty(productQuantityString) &&
                TextUtils.isEmpty(supplierNameString) &&
                TextUtils.isEmpty(supplierPhoneNumberString) &&
                mShippingFee == InventoryEntry.SHIPPING_BASE_COST &&
                mStockType == InventoryEntry.STOCK_SPECIAL_ORDER) {
            return;
        }

        int productQuantityInt = 0;

        if (!TextUtils.isEmpty(productQuantityString)) {
            productQuantityInt = Integer.parseInt(productQuantityString);
        }

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME, productNameString); // String input
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE, productPriceString); // String input
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY, productQuantityInt); // int input
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_SHIPPING_FEE, mShippingFee); // int input
        values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_STOCK_TYPE, mStockType); // int input
        values.put(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME, supplierNameString); // String input
        values.put(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, supplierPhoneNumberString); // String input

        if (mCurrentItemUri == null) {
            // Insert the new row, returning the primary key value of the new row
            Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, "New book insert failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentItemUri, values, null, null);

            if (rowsAffected == 0) {
                Toast.makeText(this, "Item update failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Item update successful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // First spinner is for shipping charges
    // Second spinner is for how the item is stocked/ordered
    private void setupSpinners() {
        // First spinner
        ArrayAdapter spinnerShippingAdapter =
                ArrayAdapter.createFromResource(this, R.array.spinner_1, android.R.layout.simple_spinner_item);
        spinnerShippingAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mShippingSpinner.setAdapter(spinnerShippingAdapter);
        mShippingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selection = (String) adapterView.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.spinner_shipping_international_free))) {
                        mShippingFee = InventoryEntry.SHIPPING_INT_FREE;
                    } else if (selection.equals(getString(R.string.spinner_shipping_local_free))) {
                        mShippingFee = InventoryEntry.SHIPPING_LOCAL_FREE;
                    } else {
                        mShippingFee = InventoryEntry.SHIPPING_BASE_COST;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mShippingFee = InventoryEntry.SHIPPING_BASE_COST;
            }
        });

        // Second spinner
        ArrayAdapter spinnerStockAdapter =
                ArrayAdapter.createFromResource(this, R.array.spinner_2, android.R.layout.simple_spinner_item);
        spinnerStockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStockSpinner.setAdapter(spinnerStockAdapter);
        mStockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selection = (String) adapterView.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.spinner_special_order))) {
                        mStockType = InventoryEntry.STOCK_SPECIAL_ORDER;
                    } else if (selection.equals(getString(R.string.spinner_custom_order))) {
                        mStockType = InventoryEntry.STOCK_CUSTOM_ORDER;
                    } else {
                        mStockType = InventoryEntry.STOCK_REPLENISH_ORDER;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mStockType = InventoryEntry.STOCK_SPECIAL_ORDER;
            }
        });
    }

    private void setupFloatingLabelErrors() {
        final TextInputLayout floatingSupplierPhoneNumberLabel = (TextInputLayout) findViewById(R.id.supplier_phone_number_text_input_layout);
        floatingSupplierPhoneNumberLabel.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO anything?
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0 && charSequence.length() < 10) {
                    floatingSupplierPhoneNumberLabel.setError("Phone Number must be 10 digits");
                    floatingSupplierPhoneNumberLabel.setErrorEnabled(true);
                } else {
                    floatingSupplierPhoneNumberLabel.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // TODO anything?
            }
        });
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("There are unsaved changes");
        builder.setPositiveButton("Discard Changes", discardButtonClickListener);
        builder.setNegativeButton("Continue editing", new DialogInterface.OnClickListener() {
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

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you wish to delete this item?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteItem();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteItem() {
        int rowsDeleted = getContentResolver().delete(mCurrentItemUri, null, null);
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Pet deleted", Toast.LENGTH_SHORT);
        }
    }
}
