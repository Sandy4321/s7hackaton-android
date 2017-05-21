package ru.s7.android.ui.mvp.view;

import ru.s7.android.model.Adventure;
import ru.s7.android.ui.mvp.MvpView;

/**
 * Created by celikindv on 20/05/2017.
 */

public interface AdventureView extends MvpView {
    void fillAdventure(Adventure adventure);
}
