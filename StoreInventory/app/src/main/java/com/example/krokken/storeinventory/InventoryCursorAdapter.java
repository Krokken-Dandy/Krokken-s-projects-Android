package com.example.krokken.storeinventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krokken.storeinventory.data.InventoryContract;
import com.example.krokken.storeinventory.data.InventoryContract.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final ViewHolder vh = new ViewHolder();
        vh.productName = view.findViewById(R.id.name);
        vh.productPrice = view.findViewById(R.id.price);
        vh.productQuantity = view.findViewById(R.id.quantity);
        vh.sellIcon = view.findViewById(R.id.sell_icon);

        // Figure out the index of each column
        int productNameIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
        int productPriceIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_PRICE);
        int productQuantityIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY);

        // Get values related to the index
        final String currentProductName = cursor.getString(productNameIndex);
        String currentProductPrice = InventoryContract.getFormattedPrice(cursor.getString(productPriceIndex), context);
        String currentProductQuantity = context.getResources().getString(R.string.popup_quantity_on_hand_text) + cursor.getString(productQuantityIndex);

        int itemIdColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
        long itemId = cursor.getLong(itemIdColumnIndex);
        final Uri currentItemUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, itemId);

        final int productQuantity = Integer.parseInt(cursor.getString(productQuantityIndex));

        // Sell one from inventory button
        vh.sellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellOne(currentItemUri, context, productQuantity, currentProductName);
            }
        });

        // Update views to display the values
        vh.productName.setText(currentProductName);
        vh.productPrice.setText(currentProductPrice);
        vh.productQuantity.setText(currentProductQuantity);
    }

    static class ViewHolder {
        private TextView productName;
        private TextView productPrice;
        private TextView productQuantity;
        private ImageView sellIcon;
    }

    private void sellOne(Uri currentItemUri, Context context, int productQuantity, String productName) {

        if (productQuantity > 0) {
            int newProductQuantity = productQuantity - 1;

            ContentValues values = new ContentValues();
            values.put(InventoryEntry.COLUMN_INVENTORY_PRODUCT_QUANTITY, newProductQuantity);
            context.getContentResolver().update(currentItemUri, values, null, null);
            Toast.makeText(context, productName + context.getString(R.string.toast_adapter_sell_one_success), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.toast_adapter_sell_one_fail), Toast.LENGTH_SHORT).show();
        }
    }
}
