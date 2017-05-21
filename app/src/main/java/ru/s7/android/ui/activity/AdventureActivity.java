package ru.s7.android.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.s7.android.App;
import ru.s7.android.R;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.component.activity.DaggerAAdventureComponent;
import ru.s7.android.di.module.activity.AAdventureModule;
import ru.s7.android.model.Adventure;
import ru.s7.android.ui.mvp.presenter.AdventurePresenter;
import ru.s7.android.ui.mvp.view.AdventureView;

public class AdventureActivity extends BaseActivity implements AdventureView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tasksRecyclerView)
    RecyclerView tasksRecyclerView;
    @BindView(R.id.logo_adventures)
    ImageView logoAdventures;
    @BindView(R.id.labelTextView)
    TextView labelTextView;
    @BindView(R.id.descTextView)
    TextView descTextView;
    @BindView(R.id.rewardTextView)
    TextView rewardTextView;
    @BindView(R.id.layout_tasks)
    RelativeLayout layoutTasks;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fabAction)
    FloatingActionButton fabAction;

    @Inject
    AdventurePresenter presenter;

    private LinearLayoutManager mTasksLinearLayoutManager;


    @OnClick(R.id.fabAction)
    void onFabActionClick() {
        presenter.fabActionClick();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        ButterKnife.bind(this);
        initPresenter();
        initViews();

        presenter.init();
    }

    void initViews() {
        initToolbar(toolbar);
        initRecyclerView();
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.adventure_title));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void initRecyclerView() {
        mTasksLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tasksRecyclerView.setLayoutManager(mTasksLinearLayoutManager);
        tasksRecyclerView.setAdapter(presenter.getTasksListAdapter());
        ((SimpleItemAnimator) tasksRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
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
        DaggerAAdventureComponent.builder().aAdventureModule(new AAdventureModule(this))
                .appComponent(appComponent).build().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void fillAdventure(Adventure adventure) {
        if (!TextUtils.isEmpty(adventure.getLogo()))
            Glide.with(this).load(adventure.getLogo()).into(logoAdventures);
        String reward = this.getString(R.string.info_reward_msg, adventure.getReward() > 0 ? adventure.getReward() : 0);

        fabAction.setImageDrawable(ContextCompat.getDrawable(this, !adventure.isFinished() ? R.drawable.ic_flight_takeoff_white_36dp : R.drawable.ic_share_white_36dp));
        rewardTextView.setText(reward);

        labelTextView.setText(TextUtils.isEmpty(adventure.getLabel()) ? "" : adventure.getLabel());
        descTextView.setText(TextUtils.isEmpty(adventure.getDesc()) ? "" : adventure.getDesc());
    }
}
