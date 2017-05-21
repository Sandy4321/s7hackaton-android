package ru.s7.android.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.s7.android.App;
import ru.s7.android.R;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.component.activity.DaggerAAllAdventuresComponent;
import ru.s7.android.di.module.activity.AAllAdventuresModule;
import ru.s7.android.ui.mvp.presenter.AllAdventuresPresenter;
import ru.s7.android.ui.mvp.view.AllAdventuresView;

public class AllAdventuresActivity extends BaseActivity implements AllAdventuresView {

    @BindView(R.id.adventuresRecyclerView)
    RecyclerView adventuresRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    AllAdventuresPresenter presenter;

    private LinearLayoutManager mAdventuresLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_adventures);
        ButterKnife.bind(this);
        initPresenter();
        initViews();

        presenter.init();
    }

    void initViews() {
        initToolbar(toolbar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdventuresLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adventuresRecyclerView.setLayoutManager(mAdventuresLinearLayoutManager);
        adventuresRecyclerView.setAdapter(presenter.getAdventuresListAdapter());
        ((SimpleItemAnimator) adventuresRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                presenter.init();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    private void initPresenter() {
        AppComponent appComponent = ((App) getApplicationContext()).appComponent();
        DaggerAAllAdventuresComponent.builder().aAllAdventuresModule(new AAllAdventuresModule(this))
                .appComponent(appComponent).build().inject(this);
        presenter.attachView(this);
    }

}
