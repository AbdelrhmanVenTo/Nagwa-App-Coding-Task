package com.vento.nagwaappcodingtask.baseClass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import burakustun.com.lottieprogressdialog.LottieDialogFragment;


public class BaseForActivity extends AppCompatActivity {

    MaterialDialog dialog;
    protected BaseForActivity activity;
    LottieDialogFragment lottieDialogFragment;
   

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }
    
	public MaterialDialog showMessage(int titleResId,
                                                     int contentResId,
                                                     int posTextResId,
                                                     int nagTextResId,
                                                     MaterialDialog.SingleButtonCallback onPos,
                                                     MaterialDialog.SingleButtonCallback onNag
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .negativeText(nagTextResId)
                .onNegative(onNag)
                .onPositive(onPos)
                .show();
        return dialog;
    }

    public MaterialDialog showMessageOk(String titleResId,
                                        String contentResId,
                                        String posTextResId
    ){
        dialog= new MaterialDialog.Builder(this)
                .title(titleResId)
                .content(contentResId)
                .positiveText(posTextResId)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }


    public MaterialDialog showProgressBar( int contentResId){
        dialog= new MaterialDialog.Builder(this)
                .content(contentResId)
                .progress(true,0)
                .cancelable(false)
                .show();
        return dialog;
    }

    public void hideProgressBar(){
        if(dialog!=null&&dialog.isShowing())
            dialog.dismiss();
    }

     public void showLottieDialog(){
        lottieDialogFragment = new LottieDialogFragment().newInstance("pckg_loading.json",true);
        lottieDialogFragment.setCancelable(false);
        lottieDialogFragment.show(activity.getFragmentManager(),"lottieDialog2");
    }

    public void hideLottieDialog(){
        lottieDialogFragment.dismiss();
    }
    
}
