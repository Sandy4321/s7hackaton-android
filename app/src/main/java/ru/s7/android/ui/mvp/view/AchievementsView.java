package ru.s7.android.ui.mvp.view;

import ru.s7.android.model.Adventure;
import ru.s7.android.model.Statistic;
import ru.s7.android.ui.mvp.MvpView;

/**
 * Created by celikindv on 20/05/2017.
 */

public interface AchievementsView extends MvpView {
    void fillStatistic(Statistic statistic);

    void showMoreAchievements();

    void showMoreAdventures();

    void showAdventure(Adventure adventure);
}
