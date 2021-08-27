package com.vento.nagwaappcodingtask.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vento.nagwaappcodingtask.baseClass.BaseViewModel;
import com.vento.nagwaappcodingtask.baseClass.MutableHelper;
import com.vento.nagwaappcodingtask.network.MyApplication;
import com.vento.nagwaappcodingtask.ui.model.VideoModelResponse;
import com.vento.nagwaappcodingtask.ui.model.VideoModelResponseItem;
import com.vento.nagwaappcodingtask.utils.Constants;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private CompositeDisposable compositeDisposable ;


    public MainViewModel(@NonNull Application application) {
        super(application);
        compositeDisposable = new CompositeDisposable();
    }

    public void getMoviesList(){
        Disposable disposable = MyApplication.getApis().getMoviesList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<List<VideoModelResponseItem>>(){

                    @Override
                    public void onSuccess(@NonNull List<VideoModelResponseItem> videoModelResponseItems) {
                        getMutableLiveData().setValue(new MutableHelper(Constants.MOVIES_SUCCESS,
                                videoModelResponseItems));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getLocalizedMessage() );
                        getMutableLiveData().setValue(new MutableHelper(Constants
                                .NETWORK_CONNECTION_ERROR,null));
                    }
                });


        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
