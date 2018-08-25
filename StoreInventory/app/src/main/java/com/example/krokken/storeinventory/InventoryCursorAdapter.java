package com.example.krokken.storeinventory;

import android.content.Context;
import android.database.Cursor;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.krokken.storeinventory.data.InventoryContract.InventoryEntry;

import java.text.NumberFormat;
import java.util.Locale;

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter (Context context, Cursor c) {
        super(context, c, 0 );
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder vh = new ViewHolder();
        vh.productName = (TextView) view.findViewById(R.id.name);
        vh.supplierName = (TextView) view.findViewById(R.id.summary);

        // Figure out the index of each column
        int productNameIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
        int supplierNameIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);

        // Get values related to the index
        String currentProductName = cursor.getString(productNameIndex);
        String currentSupplierName = cursor.getString(supplierNameIndex);

        // Update views to display the values
        vh.productName.setText(currentProductName);
        vh.supplierName.setText(currentSupplierName);
    }

    static class ViewHolder{
        private TextView productName;
        private TextView supplierName;
    }
}
