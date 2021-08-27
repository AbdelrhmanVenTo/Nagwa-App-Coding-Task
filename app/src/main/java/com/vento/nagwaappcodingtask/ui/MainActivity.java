package com.vento.nagwaappcodingtask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vento.nagwaappcodingtask.R;
import com.vento.nagwaappcodingtask.baseClass.BaseForActivity;
import com.vento.nagwaappcodingtask.baseClass.MutableHelper;
import com.vento.nagwaappcodingtask.databinding.ActivityMainBinding;
import com.vento.nagwaappcodingtask.ui.model.VideoModelResponseItem;
import com.vento.nagwaappcodingtask.utils.Constants;

import java.util.List;

public class MainActivity extends BaseForActivity {

    ActivityMainBinding binding;
    MainViewModel viewModel;
    MoviesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
        initView();
        viewModel.getMoviesList();
        onObserverListener();

    }
    private void init() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setLifecycleOwner(this);
    }

    private void initView(){
        adapter = new MoviesAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        binding.rvMoviesList.setAdapter(adapter);
        binding.rvMoviesList.setLayoutManager(layoutManager);
    }


    public void onObserverListener(){
        viewModel.getMutableLiveData().observe(activity, new Observer<MutableHelper>() {
            @Override
            public void onChanged(MutableHelper mutableHelper) {
                if (mutableHelper.key==Constants.MOVIES_SUCCESS){
                    List<VideoModelResponseItem> itemList = (List<VideoModelResponseItem>) mutableHelper.value;
                    adapter = new MoviesAdapter(itemList);
                    binding.rvMoviesList.setAdapter(adapter);
                    adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int pos, VideoModelResponseItem responseItem) {
                            Toast.makeText(activity, "download", Toast.LENGTH_SHORT).show();
                        }
                    });
                   

                }else if (mutableHelper.key== Constants.NETWORK_CONNECTION_ERROR){
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}