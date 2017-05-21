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
import ru.s7.android.di.component.activity.DaggerANotificationsComponent;
import ru.s7.android.di.module.activity.ANotificationsModule;
import ru.s7.android.ui.mvp.presenter.NotificationsPresenter;
import ru.s7.android.ui.mvp.view.NotificationsView;

public class NotificationsActivity extends BaseActivity implements NotificationsView {

    @BindView(R.id.notificationsRecyclerView)
    RecyclerView notificationsRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    NotificationsPresenter presenter;
    private LinearLayoutManager mNotificationsLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notifications);
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
        mNotificationsLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        notificationsRecyclerView.setLayoutManager(mNotificationsLinearLayoutManager);
        notificationsRecyclerView.setAdapter(presenter.getNotificationsListAdapter());
        ((SimpleItemAnimator) notificationsRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
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
        DaggerANotificationsComponent.builder().aNotificationsModule(new ANotificationsModule(this))
                .appComponent(appComponent).build().inject(this);

        presenter.attachView(this);
    }

}
