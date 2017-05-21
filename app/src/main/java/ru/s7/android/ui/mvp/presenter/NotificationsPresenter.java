package ru.s7.android.ui.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.model.Achievement;
import ru.s7.android.model.NotificationPush;
import ru.s7.android.ui.activity.BaseActivity;
import ru.s7.android.ui.adapter.NotificationsListAdapter;
import ru.s7.android.ui.mvp.Presenter;
import ru.s7.android.ui.mvp.view.NotificationsView;
import ru.s7.android.utils.Constants;
import ru.s7.android.utils.LoremIpsum;
import ru.s7.android.utils.Utils;

/**
 * Created by celikindv on 20/05/2017.
 */

public class NotificationsPresenter extends Presenter<NotificationsView> {

    Context context;
    BaseActivity activity;
    NotificationsView notificationsView;
    NotificationsListAdapter notificationsListAdapter;
    NotificationsListAdapter.OnNotificationListener onAchievementListener;

    @Inject
    DataProvider dataProvider;

    public NotificationsPresenter(Context context) {
        Injector(context).inject(this);
        this.context = context;
        this.activity = (BaseActivity) context;
        this.notificationsListAdapter = new NotificationsListAdapter(context);
        this.onAchievementListener = new NotificationsListAdapter.OnNotificationListener() {
            @Override
            public void onClick(int adapterPosition) {
                NotificationPush notificationPush = notificationsListAdapter.getNotificationPush(adapterPosition);
            }
        };
        notificationsListAdapter.setListener(onAchievementListener);
    }

    public void init() {
        addAchievement();
    }

    void addAchievement() {
        String logo = "http://95.213.200.71/media/uploads/achievements/GoingMoblie.png";
        ArrayList<NotificationPush> notificationPushes = new ArrayList<>();
        notificationPushes.add(new NotificationPush(logo,
                "Получена награда \"Мобильность\"",
                "Награда получена за установку мобильного приложения",
                System.currentTimeMillis()));
        notificationsListAdapter.setListNotification(notificationPushes);
    }

    void fillStub() {
        int level = Utils.randInt(1, 20);
        int maxScoreInLevel = level * Constants.THREASHOLD;
        int currentFlights = (int) Math.ceil(maxScoreInLevel / 500);
        int achievementsCount = Utils.randInt((int) Math.ceil(currentFlights / 2), currentFlights);

        ArrayList<NotificationPush> notificationPushes = new ArrayList<>();
        LoremIpsum loremIpsum = new LoremIpsum();
        Date date = new Date();
        for (int i = 0; i < achievementsCount; i++) {
            String logo = "http://lorempixel.com/200/200/abstract?r=" + Math.random();
            notificationPushes.add(new NotificationPush(logo,
                    loremIpsum.words(Utils.randInt(3, 6)),
                    loremIpsum.words(Utils.randInt(6, 12)),
                    date.getTime() - (Utils.randInt(1, 96) * 1000 * 60)
            ));
        }

        notificationsListAdapter.setListNotification(notificationPushes);
    }


    private void showAchievement(Achievement achievement) {
        activity.showAchievement(achievement);
    }

    public NotificationsListAdapter getNotificationsListAdapter() {
        return notificationsListAdapter;
    }


    @Override
    public void attachView(NotificationsView view) {
        notificationsView = view;
    }

    @Override
    public void detachView() {
        notificationsView = null;
    }
}
