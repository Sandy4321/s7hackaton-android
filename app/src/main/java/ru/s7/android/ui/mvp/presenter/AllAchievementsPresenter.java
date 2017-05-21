package ru.s7.android.ui.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.inject.Inject;

import ru.s7.android.io.rest.AsyncData;
import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.io.rest.response.Achievements;
import ru.s7.android.model.Achievement;
import ru.s7.android.ui.activity.BaseActivity;
import ru.s7.android.ui.adapter.AchievementsListAdapter;
import ru.s7.android.ui.mvp.Presenter;
import ru.s7.android.ui.mvp.view.AllAchievementsView;
import ru.s7.android.utils.Constants;
import ru.s7.android.utils.LoremIpsum;
import ru.s7.android.utils.Utils;

/**
 * Created by celikindv on 20/05/2017.
 */

public class AllAchievementsPresenter extends Presenter<AllAchievementsView> {

    Context context;
    BaseActivity activity;
    AllAchievementsView allAchievementsView;
    AchievementsListAdapter achievementsListAdapter;
    AchievementsListAdapter.OnAchievementListener onAchievementListener;

    @Inject
    DataProvider dataProvider;

    public AllAchievementsPresenter(Context context) {
        Injector(context).inject(this);
        this.context = context;
        this.activity = (BaseActivity) context;
        this.achievementsListAdapter = new AchievementsListAdapter(context);
        this.onAchievementListener = new AchievementsListAdapter.OnAchievementListener() {
            @Override
            public void onClick(int adapterPosition) {
                Achievement achievement = achievementsListAdapter.getAchievement(adapterPosition);
                if (achievement != null)
                    showAchievement(achievement);
            }
        };
        achievementsListAdapter.setListener(onAchievementListener);
    }

    public void init() {
        dataProvider.getAchievements(new AsyncData<Achievements>() {
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
        // fillStub();
    }

    void fillStub() {
        int level = Utils.randInt(1, 20);
        int maxScoreInLevel = level * Constants.THREASHOLD;
        int currentFlights = (int) Math.ceil(maxScoreInLevel / 500);
        int achievementsCount = Utils.randInt((int) Math.ceil(currentFlights / 2), currentFlights);

        ArrayList<Achievement> achievements = new ArrayList<>();
        LoremIpsum loremIpsum = new LoremIpsum();
        Date date = new Date();
        for (int i = 0; i < achievementsCount; i++) {
            String logo = "http://lorempixel.com/200/200/abstract?r=" + Math.random();
            achievements.add(new Achievement(logo,
                    loremIpsum.words(Utils.randInt(3, 6)),
                    loremIpsum.words(Utils.randInt(6, 12)),
                    0, 0,
                    date.getTime() - (Utils.randInt(1, 96) * 1000 * 60),
                    Utils.randBoolean()
            ));
            Collections.sort(achievements, new Comparator<Achievement>() {
                @Override
                public int compare(Achievement o1, Achievement o2) {
                    return o1.isUnlocked() == o2.isUnlocked() ? 0 : -1;
                }
            });

        }

        achievementsListAdapter.setListAchievement(achievements);
    }


    private void showAchievement(Achievement achievement) {
        activity.showAchievement(achievement);
    }

    public AchievementsListAdapter getAchievementsListAdapter() {
        return achievementsListAdapter;
    }


    @Override
    public void attachView(AllAchievementsView view) {
        allAchievementsView = view;
    }

    @Override
    public void detachView() {
        allAchievementsView = null;
    }
}
