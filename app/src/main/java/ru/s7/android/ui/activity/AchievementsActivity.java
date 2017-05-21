package ru.s7.android.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.s7.android.App;
import ru.s7.android.R;
import ru.s7.android.di.component.AppComponent;
import ru.s7.android.di.component.activity.DaggerAAachievementsComponent;
import ru.s7.android.di.module.activity.AAchievementsModule;
import ru.s7.android.model.Adventure;
import ru.s7.android.model.Statistic;
import ru.s7.android.ui.mvp.presenter.AchievementsPresenter;
import ru.s7.android.ui.mvp.view.AchievementsView;
import ru.s7.android.utils.Constants;

public class AchievementsActivity extends BaseActivity implements AchievementsView {

    @BindView(R.id.doneAdventuresRecyclerView)
    RecyclerView doneAdventuresRecyclerView;
    @BindView(R.id.lastAchievements_hint)
    TextView lastAchievementsHint;
    @BindView(R.id.moreAchievementsBtn)
    Button moreAchievementsBtn;
    @BindView(R.id.lastAchievementsRecyclerView)
    RecyclerView lastAchievementsRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_scores)
    RoundCornerProgressBar progressScores;
    @BindView(R.id.progress_scores_level)
    TextView progressScoresLevel;
    @BindView(R.id.progress_scores_exp)
    TextView progressScoresExp;
    @BindView(R.id.progress_scores_result_text)
    TextView progressScoresResultText;
    @BindView(R.id.progress_scores_hint)
    TextView progressScoresHint;

    @BindView(R.id.aeroport_count_hint)
    TextView aeroportCountHint;
    @BindView(R.id.city_count_hint)
    TextView cityCountHint;
    @BindView(R.id.country_count_hint)
    TextView countryCountHint;
    @BindView(R.id.plane_count_hint)
    TextView planeCountHint;

    @BindView(R.id.aeroportCount)
    TextView aeroportCount;
    @BindView(R.id.cityCount)
    TextView cityCount;
    @BindView(R.id.countryCount)
    TextView countryCount;
    @BindView(R.id.planeCount)
    TextView planeCount;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    AchievementsPresenter presenter;

    private LinearLayoutManager mLastAchievementsLinearLayoutManager;
    private LinearLayoutManager mDoneAdventuresLinearLayoutManager;
    private long lastBackPressed;
    private boolean updated = false;

    @OnClick(R.id.moreAchievementsBtn)
    void onMoreAchievementsBtn() {
        showMoreAchievements();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        ButterKnife.bind(this);
        initPresenter();
        initViews();

        presenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    private void initPresenter() {
        AppComponent appComponent = ((App) getApplicationContext()).appComponent();
        DaggerAAachievementsComponent.builder().aAchievementsModule(new AAchievementsModule(this))
                .appComponent(appComponent).build().inject(this);
        presenter.attachView(this);
    }


    void initViews() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
        initRecyclerView();
    }


    private void initRecyclerView() {
        mLastAchievementsLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mDoneAdventuresLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        lastAchievementsRecyclerView.setLayoutManager(mLastAchievementsLinearLayoutManager);
        lastAchievementsRecyclerView.setAdapter(presenter.getAchievementsListAdapter());
        ((SimpleItemAnimator) lastAchievementsRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        doneAdventuresRecyclerView.setLayoutManager(mDoneAdventuresLinearLayoutManager);
        doneAdventuresRecyclerView.setAdapter(presenter.getAdventuresPreviewListAdapter());
        ((SimpleItemAnimator) lastAchievementsRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (lastBackPressed < System.currentTimeMillis() - Constants.BACKPRESS_DELAY) {
            Toast.makeText(this, R.string.exit_msg, Toast.LENGTH_SHORT).show();
            lastBackPressed = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.notificationsList:
                showNotifications();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void fillStatistic(Statistic statistic) {
        if (updated)
            return;
        updated = true;
        showAchievementDialog();
        int max = statistic.getLevel() * Constants.THREASHOLD;
        progressScores.setMax(max);
        progressScores.setProgress(statistic.getCurrentScoreCount());
        progressScoresLevel.setText(getString(R.string.currentLevel, statistic.getLevel()));
        progressScoresExp.setText(getString(R.string.exp_text, statistic.getCurrentScoreCount(), max));

        progressScoresResultText.setText(getString(R.string.your_visits_msg, getResources().getQuantityString(R.plurals.plurals_flight, statistic.getCurrentFlightsCount(), statistic.getCurrentFlightsCount())));

        progressScoresHint.setText(getString(R.string.info_level_msg, statistic.getLevel() + 1));
        aeroportCount.setText(String.valueOf(statistic.getCurrentFlightsCount()));
        cityCount.setText(String.valueOf(statistic.getCityCount()));
        countryCount.setText(String.valueOf(statistic.getCountryCount()));
        planeCount.setText(String.valueOf(statistic.getPlaneCount()));

        aeroportCountHint.setText(getResources().getQuantityString(R.plurals.plurals_aeroport, statistic.getAeroportCount(), statistic.getAeroportCount()));
        cityCountHint.setText(getResources().getQuantityString(R.plurals.plurals_city, statistic.getCityCount(), statistic.getCityCount()));
        countryCountHint.setText(getResources().getQuantityString(R.plurals.plurals_country, statistic.getCountryCount(), statistic.getCountryCount()));
        planeCountHint.setText(getResources().getQuantityString(R.plurals.plurals_aero, statistic.getPlaneCount(), statistic.getPlaneCount()));

    }

    @Override
    public void showMoreAchievements() {
        super.showMoreAchievements();
    }

    @Override
    public void showMoreAdventures() {
        super.showMoreAdventures();
    }


    @Override
    public void showAdventure(Adventure adventure) {
        super.showAdventure(adventure);
    }
}
