package com.hossam.codesroots.presentation.availablebanks;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.availablebanks.adapters.BanksAdapter;

public class BanksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView title;
    BanksViewModel banksViewModel;
    FrameLayout loadinng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        recyclerView = findViewById(R.id.banks_recycler);
        title = findViewById(R.id.title);
        loadinng = findViewById(R.id.progress);
        title.setText(getText(R.string.available_banks));
        banksViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(BanksViewModel.class);
        banksViewModel.banksViewModel.observe(this, availableBanks ->
                {
                    if (availableBanks!=null)
                    recyclerView.setAdapter(new BanksAdapter(this,availableBanks.getData()));
                    loadinng.setVisibility(View.GONE);
                });

        banksViewModel.throwableMutableLiveData.observe(this,throwable ->
                {
                    if (throwable!=null)
                    Toast.makeText(this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this,getText(R.string.error_add),Toast.LENGTH_SHORT).show();
                    loadinng.setVisibility(View.GONE);
                }
                );
    }

    private BanksViewModelFactory getViewModelFactory ()
    {
        return new BanksViewModelFactory(this.getApplication());
    }

}
