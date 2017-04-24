package com.rgand.x_prt.lastfmhits.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.rgand.x_prt.lastfmhits.R;
import com.rgand.x_prt.lastfmhits.dialog.SpinnerDialog;

/**
 * Created by x_prt on 24.04.2017
 */

public class BaseActivity extends AppCompatActivity {

    private AlertDialog networkDialog;
    private SpinnerDialog spinnerDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void showInternetConnectionDialog(DialogInterface.OnDismissListener listener) {
        if (networkDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            networkDialog = builder.create();
            networkDialog.setOnDismissListener(listener);
            networkDialog.setMessage(getString(R.string.no_internet_message_txt));
            networkDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                    getString(R.string.ok_txt), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            networkDialog.dismiss();
                        }
                    });
        }
        networkDialog.show();
    }

    public void checkInternetConnection(Context context, DialogInterface.OnDismissListener listener) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            showInternetConnectionDialog(listener);
        }
    }

    public void showProgressBar() {
        spinnerDialog = SpinnerDialog.newInstance();
        spinnerDialog.show(getSupportFragmentManager(), this.getClass().getSimpleName());
    }

    public void hideProgressBar() {
        spinnerDialog.dismiss();
    }
}
