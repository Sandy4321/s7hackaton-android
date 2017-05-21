package ru.s7.android.ui.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import ru.s7.android.io.rest.AsyncData;
import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.io.rest.response.Adventures;
import ru.s7.android.model.Adventure;
import ru.s7.android.ui.activity.BaseActivity;
import ru.s7.android.ui.adapter.AdventuresListAdapter;
import ru.s7.android.ui.mvp.Presenter;
import ru.s7.android.ui.mvp.view.AllAdventuresView;
import ru.s7.android.utils.Constants;
import ru.s7.android.utils.LoremIpsum;
import ru.s7.android.utils.Utils;

/**
 * Created by celikindv on 20/05/2017.
 */

public class AllAdventuresPresenter extends Presenter<AllAdventuresView> {

    Context context;
    BaseActivity activity;
    AllAdventuresView allAdventuresView;
    AdventuresListAdapter adventuresListAdapter;
    AdventuresListAdapter.OnAdventureListener onAdventureListener;


    @Inject
    DataProvider dataProvider;

    public AllAdventuresPresenter(Context context) {
        Injector(context).inject(this);
        this.context = context;
        this.adventuresListAdapter = new AdventuresListAdapter(context);
        this.onAdventureListener = new AdventuresListAdapter.OnAdventureListener() {
            @Override
            public void onClick(int adapterPosition) {
                Adventure adventure = adventuresListAdapter.getAdventure(adapterPosition);
                if (adventure != null)
                    showAdventure(adventure);
            }
        };
        adventuresListAdapter.setListener(onAdventureListener);
    }

    private void showAdventure(Adventure adventure) {
        activity = (BaseActivity) context;
        activity.showAdventure(adventure);
    }

    public void init() {
        dataProvider.getAdventures(new AsyncData<Adventures>() {
            @Override
            public void onSuccess(Adventures data) {
                ArrayList<Adventure> adventures = data.getAdventures();
                Collections.sort(adventures, new Comparator<Adventure>() {
                    @Override
                    public int compare(Adventure o1, Adventure o2) {
                        return o1.isFinished() == o2.isFinished() ? 0 : -1;
                    }
                });
                adventuresListAdapter.setListAdventure(adventures);
            }

            @Override
            public void onError() {

            }
        });
    }

    void fillStub() {
        int level = Utils.randInt(1, 20);
        int maxScoreInLevel = level * Constants.THREASHOLD;
        int currentFlights = (int) Math.ceil(maxScoreInLevel / 500);
        int adventuresCount = Utils.randInt((int) Math.ceil(currentFlights / 2), currentFlights);

        ArrayList<Adventure> adventures = new ArrayList<>();
        LoremIpsum loremIpsum = new LoremIpsum();
        for (int i = 0; i < adventuresCount; i++) {
            String logo = "http://lorempixel.com/400/200/city?r=" + Math.random();
            adventures.add(new Adventure(logo,
                    loremIpsum.words(Utils.randInt(3, 6)),
                    loremIpsum.words(Utils.randInt(6, 12)),
                    Utils.randInt(0, 50),
                    Utils.randBoolean()));
        }
        Collections.sort(adventures, new Comparator<Adventure>() {
            @Override
            public int compare(Adventure o1, Adventure o2) {
                return o1.isFinished() == o2.isFinished() ? 0 : -1;
            }
        });

        adventuresListAdapter.setListAdventure(adventures);
    }


    public AdventuresListAdapter getAdventuresListAdapter() {
        return adventuresListAdapter;
    }


    @Override
    public void attachView(AllAdventuresView view) {
        allAdventuresView = view;
    }

    @Override
    public void detachView() {
        allAdventuresView = null;
    }
}
