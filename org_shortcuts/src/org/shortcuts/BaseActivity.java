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
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class BaseActivity extends Activity {
    private TextView mDescription;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_activity);

        mDescription = (TextView) findViewById(R.id.base_activity_description);

        // load html from html file from /res/raw
        String descriptionText = Utils.readContentFromResource(this, R.raw.description);

        // set text from resources with html markup
        mDescription.setText(Html.fromHtml(descriptionText));
        // make links work
        mDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }
}