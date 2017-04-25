package com.rgand.x_prt.lastfmhits.base;

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

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

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
            networkDialog.setButton(BUTTON_NEGATIVE,
                    getString(R.string.exit_txt), onDialogClickListener);
            networkDialog.setButton(BUTTON_POSITIVE,
                    getString(R.string.retry_txt), onDialogClickListener);
        }
        networkDialog.show();
    }

    private DialogInterface.OnClickListener onDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case BUTTON_NEGATIVE:
                    finishAffinity();
                    break;
                case BUTTON_POSITIVE:
                    networkDialog.dismiss();
                    break;
            }
        }
    };

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
