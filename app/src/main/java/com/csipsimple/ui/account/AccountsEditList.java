/**
 * Copyright (C) 2010-2012 Regis Montoya (aka r3gis - www.r3gis.fr)
 * This file is part of CSipSimple.
 *
 *  CSipSimple is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  If you own a pjsip commercial license you can also redistribute it
 *  and/or modify it under the terms of the GNU Lesser General Public License
 *  as an android library.
 *
 *  CSipSimple is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CSipSimple.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.csipsimple.ui.account;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.csipsimple.R;
import com.csipsimple.utils.Log;

public class AccountsEditList extends AppCompatActivity {
	private static String TAG = "ricky";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "accntEditLst onCreate");
		setContentView(R.layout.accounts_view);

		ActionBar actBar = getActionBar();
		/*actBar.setDisplayHomeAsUpEnabled(false);
		actBar.setDisplayShowTitleEnabled(false);*/
		actBar.hide();
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		Log.d(TAG, "accntEditLst onOptionsItemSelected");
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "accntEditLst onResume");
		super.onResume();
	}

	@Override
	public void onBackPressed() {
		Log.d(TAG, "accntEditLst onBackPress");
		if(getSupportFragmentManager().getBackStackEntryCount() > 0){
			getSupportFragmentManager().popBackStack();
		}else{
			super.onBackPressed();
		}
	}
}
