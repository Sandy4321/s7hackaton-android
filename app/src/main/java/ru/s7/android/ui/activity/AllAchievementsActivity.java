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
import ru.s7.android.di.component.activity.DaggerAAllAchievementsComponent;
import ru.s7.android.di.module.activity.AAllAchievementsModule;
import ru.s7.android.ui.mvp.presenter.AllAchievementsPresenter;
import ru.s7.android.ui.mvp.view.AllAchievementsView;

public class AllAchievementsActivity extends BaseActivity implements AllAchievementsView {

    @BindView(R.id.achievementsRecyclerView)
    RecyclerView achievementsRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    AllAchievementsPresenter presenter;
    private LinearLayoutManager mAchievementsLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_achievements);
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
        mAchievementsLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        achievementsRecyclerView.setLayoutManager(mAchievementsLinearLayoutManager);
        achievementsRecyclerView.setAdapter(presenter.getAchievementsListAdapter());
        ((SimpleItemAnimator) achievementsRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
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
        DaggerAAllAchievementsComponent.builder().aAllAchievementsModule(new AAllAchievementsModule(this))
                .appComponent(appComponent).build().inject(this);

        presenter.attachView(this);
    }

}
