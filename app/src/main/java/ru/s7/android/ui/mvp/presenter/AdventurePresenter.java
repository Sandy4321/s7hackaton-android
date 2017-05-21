package ru.s7.android.ui.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import javax.inject.Inject;

import ru.s7.android.R;
import ru.s7.android.io.rest.DataProvider;
import ru.s7.android.model.Adventure;
import ru.s7.android.model.Task;
import ru.s7.android.ui.activity.BaseActivity;
import ru.s7.android.ui.adapter.TasksListAdapter;
import ru.s7.android.ui.mvp.Presenter;
import ru.s7.android.ui.mvp.view.AdventureView;
import ru.s7.android.utils.Constants;
import ru.s7.android.utils.Utils;

/**
 * Created by celikindv on 20/05/2017.
 */

public class AdventurePresenter extends Presenter<AdventureView> {

    Context context;
    BaseActivity activity;
    AdventureView adventureView;
    TasksListAdapter tasksListAdapter;
    TasksListAdapter.OnTaskListener onTaskListener;
    Adventure adventure;

    @Inject
    DataProvider dataProvider;

    public AdventurePresenter(Context context) {
        Injector(context).inject(this);
        this.context = context;
        this.activity = (BaseActivity) context;
        this.tasksListAdapter = new TasksListAdapter(context);

        this.onTaskListener = new TasksListAdapter.OnTaskListener() {
            @Override
            public void onClick(int adapterPosition) {
                Task task = tasksListAdapter.getTask(adapterPosition);
                if (task != null)
                    showTask(task);
            }
        };

        tasksListAdapter.setListener(onTaskListener);
    }

    public void init() {
        if (activity.getIntent() != null && activity.getIntent().hasExtra(Constants.ADVENTURE)) {
            adventure = Parcels.unwrap(activity.getIntent().getParcelableExtra(Constants.ADVENTURE));
            fillStub(adventure);
        } else {
            activity.finish();
        }
    }

    void fillStub(Adventure adventure) {
        int tasksCount = Utils.randInt(1, 3);
        if (adventure.getTaskList().size() > 0)
            tasksListAdapter.setListTasks(adventure.getTaskList());
        adventureView.fillAdventure(adventure);

    }


    private void showTask(Task task) {

    }

    public TasksListAdapter getTasksListAdapter() {
        return tasksListAdapter;
    }


    @Override
    public void attachView(AdventureView view) {
        adventureView = view;
    }

    @Override
    public void detachView() {
        adventureView = null;
    }

    public void fabActionClick() {
        if (adventure != null) {
            if (adventure.isFinished()) {
                Utils.share(context, String.format(Locale.getDefault(), context.getString(R.string.earn_points_text), adventure.getLabel(),
                        context.getResources().getQuantityString(R.plurals.plurals_points, adventure.getReward(), adventure.getReward())
                ));
            } else {
                Toast.makeText(context, R.string.create_adventure_stub, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
