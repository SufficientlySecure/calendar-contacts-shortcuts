/*
 * Copyright (C) 2012 Dominik Schürmann <dominik@dominikschuermann.de>
 *
 * This file is part of Shortcuts for Calendar/Contacts.
 * 
 * Shortcuts for Calendar/Contacts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Shortcuts for Calendar/Contacts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Shortcuts for Calendar/Contacts.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.shortcuts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;

public class ShortcutActivity extends Activity {
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);

        final CharSequence[] items = getResources().getStringArray(R.array.shortcut_new);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(R.string.shortcut_dialog_title);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                // Intent of shortcut
                Intent shortcutIntent = new Intent(Intent.ACTION_INSERT);

                ShortcutIconResource iconResource = null;

                // make shortcut based on selection
                switch (item) {
                case 0:
                    shortcutIntent.setType("vnd.android.cursor.dir/event");

                    // icon
                    iconResource = ShortcutIconResource.fromContext(mActivity,
                            R.drawable.ic_launcher_calendar);

                    break;
                case 1:
                    shortcutIntent.setType("vnd.android.cursor.dir/person");

                    // icon
                    iconResource = ShortcutIconResource.fromContext(mActivity,
                            R.drawable.ic_launcher_shortcut_contact);

                    break;
                }

                // The result we are passing back from this activity
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, items[item]);
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
                setResult(RESULT_OK, intent);

                // Must call finish for result to be returned immediately
                finish();
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);

                // Must call finish for result to be returned immediately
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
