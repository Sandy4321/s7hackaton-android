package ru.s7.android.ui.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import ru.s7.android.io.rest.AsyncData;
import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.io.rest.response.Achievements;
import ru.s7.android.io.rest.response.Adventures;
import ru.s7.android.model.Achievement;
import ru.s7.android.model.Adventure;
import ru.s7.android.model.Statistic;
import ru.s7.android.ui.activity.BaseActivity;
import ru.s7.android.ui.adapter.AchievementsListAdapter;
import ru.s7.android.ui.adapter.AdventuresPreviewListAdapter;
import ru.s7.android.ui.mvp.Presenter;
import ru.s7.android.ui.mvp.view.AchievementsView;
import ru.s7.android.utils.Constants;
import ru.s7.android.utils.Utils;

/**
 * Created by celikindv on 20/05/2017.
 */

public class AchievementsPresenter extends Presenter<AchievementsView> {

    Context context;
    BaseActivity activity;
    AchievementsView achievementsView;
    AchievementsListAdapter achievementsListAdapter;
    AdventuresPreviewListAdapter adventuresPreviewListAdapter;
    AdventuresPreviewListAdapter.OnAdventureListener onAdventureListener;
    AchievementsListAdapter.OnAchievementListener onAchievementListener;

    @Inject
    DataProvider dataProvider;

    public AchievementsPresenter(final Context context) {
        Injector(context).inject(this);
        this.context = context;
        this.activity = (BaseActivity) context;
        this.achievementsListAdapter = new AchievementsListAdapter(context);
        this.adventuresPreviewListAdapter = new AdventuresPreviewListAdapter();
        this.onAchievementListener = new AchievementsListAdapter.OnAchievementListener() {
            @Override
            public void onClick(int adapterPosition) {
                Achievement achievement = achievementsListAdapter.getAchievement(adapterPosition);
                if (achievement != null)
                    showAchievement(achievement);
            }
        };
        this.onAdventureListener = new AdventuresPreviewListAdapter.OnAdventureListener() {
            @Override
            public void onClick(int adapterPosition) {
                Adventure adventure = adventuresPreviewListAdapter.getAdventure(adapterPosition);
                if (adventure != null)
                    showAdventure(adventure);
            }

            @Override
            public void onClickMore() {
                achievementsView.showMoreAdventures();
            }

            @Override
            public void onClickShare(int adapterPosition) {
                Adventure adventure = adventuresPreviewListAdapter.getAdventure(adapterPosition);
                if (adventure != null)
                    Utils.share(context,adventure.getLabel() + "\n" + adventure.getDesc());
            }
        };

        adventuresPreviewListAdapter.setListener(onAdventureListener);
        achievementsListAdapter.setListener(onAchievementListener);
    }

    public void init() {
//        dataProvider.getProfile(new AsyncData<GamerProfile>() {
//            @Override
//            public void onSuccess(GamerProfile data) {
//
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });

        dataProvider.getMyAdventures(new AsyncData<Adventures>() {
            @Override
            public void onSuccess(Adventures data) {
                ArrayList<Adventure> adventures = data.getAdventures();
                Collections.sort(adventures, new Comparator<Adventure>() {
                    @Override
                    public int compare(Adventure o1, Adventure o2) {
                        return o1.isFinished() == o2.isFinished() ? 0 : -1;
                    }
                });
                adventuresPreviewListAdapter.setListAdventure(adventures);
            }

            @Override
            public void onError() {

            }
        });

        dataProvider.getMyAchievements(new AsyncData<Achievements>() {
            @Override
            public void onSuccess(Achievements data) {
                ArrayList<Achievement> achievements = data.getAchievements();
                Collections.sort(achievements, new Comparator<Achievement>() {
                    @Override
                    public int compare(Achievement o1, Achievement o2) {
                        return o1.isUnlocked() == o2.isUnlocked() ? 0 : -1;
                    }
                });
                achievementsListAdapter.setListAchievement(achievements);
            }

            @Override
            public void onError() {

            }
        });
        fillStub();
    }

    void fillStub() {
        int level = Utils.randInt(1, 20);
        int maxScoreInLevel = level * Constants.THREASHOLD;
        int currentScore = maxScoreInLevel - Utils.randInt(500, maxScoreInLevel - 500);
        int currentFlights = (int) Math.ceil(maxScoreInLevel / 500);
        int currentAeroport = (int) Math.ceil((currentFlights / 100) * 50);
        int currentCity = (int) Math.ceil((currentFlights / 100) * 60) + 2;
        int countryCount = Utils.randInt(1, currentCity + 1);
        int planeCount = Utils.randInt(1, 6);
        Statistic statistic = new Statistic(level, currentScore, currentFlights, currentAeroport, currentCity, countryCount, planeCount);
        achievementsView.fillStatistic(statistic);

//        int adventuresCount = Utils.randInt(1, 3);
//        int achievementsCount = Utils.randInt((int) Math.ceil(currentFlights / 2), currentFlights);
//
//        ArrayList<Achievement> achievements = new ArrayList<>();
//        ArrayList<Adventure> adventures = new ArrayList<>();
//        LoremIpsum loremIpsum = new LoremIpsum();
//        Date date = new Date();
//        for (int i = 0; i < achievementsCount; i++) {
//            String logo = "http://lorempixel.com/200/200/abstract?r=" + Math.random();
//            achievements.add(new Achievement(logo,
//                    loremIpsum.words(Utils.randInt(3, 6)),
//                    loremIpsum.words(Utils.randInt(6, 12)),
//                    date.getTime() - (Utils.randInt(24, 24 * 30) * 1000 * 60)
//            ));
//        }
//        for (int i = 0; i < adventuresCount; i++) {
//            String logo = "http://lorempixel.com/400/200/city?r=" + Math.random();
//            adventures.add(new Adventure(logo,
//                    loremIpsum.words(Utils.randInt(3, 6)),
//                    loremIpsum.words(Utils.randInt(6, 12)),
//                    Utils.randInt(500, 1000),
//                    Utils.randBoolean()));
//
//        }
//        Collections.sort(achievements, new Comparator<Achievement>() {
//            @Override
//            public int compare(Achievement o1, Achievement o2) {
//                return o1.isUnlocked() == o2.isUnlocked() ? 0 : -1;
//            }
//        });
//
//        Collections.sort(adventures, new Comparator<Adventure>() {
//            @Override
//            public int compare(Adventure o1, Adventure o2) {
//                return o1.isFinished() == o2.isFinished() ? 0 : -1;
//            }
//        });
//        achievementsListAdapter.setListAchievement(achievements);
//        adventuresPreviewListAdapter.setListAdventure(adventures);
    }

    public void showAdventure(Adventure adventure) {
        activity.showAdventure(adventure);
    }

    private void showAchievement(Achievement achievement) {
        activity.showAchievement(achievement);
    }

    public AchievementsListAdapter getAchievementsListAdapter() {
        return achievementsListAdapter;
    }

    public AdventuresPreviewListAdapter getAdventuresPreviewListAdapter() {
        return adventuresPreviewListAdapter;
    }

    @Override
    public void attachView(AchievementsView view) {
        achievementsView = view;
    }

    @Override
    public void detachView() {
        achievementsView = null;
    }
}
