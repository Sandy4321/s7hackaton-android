package ru.s7.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.parceler.Parcels;

import ru.s7.android.R;
import ru.s7.android.model.Achievement;
import ru.s7.android.model.Adventure;
import ru.s7.android.ui.fragment.AchievementDialogFragment;
import ru.s7.android.ui.fragment.VacationDialogFragment;

import static ru.s7.android.utils.Constants.ADVENTURE;

/**
 * Created by celikindv on 20/05/2017.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * This method initialize Toolbar.
     *
     * @param mToolbar the m toolbar
     */
    void initToolbar(Toolbar mToolbar) {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    public void showAchievement(Achievement achievement) {
        AchievementDialogFragment
                .with(getSupportFragmentManager())
                .achievement(achievement)
                .show();
    }

    void showAchievements() {
        Intent intent = new Intent(this, AchievementsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent, new Bundle());
        finish();
    }

    void showVacation() {
        VacationDialogFragment
                .with(getSupportFragmentManager())
                .show();
    }

    void showMoreAchievements() {
        Intent intent = new Intent(this, AllAchievementsActivity.class);
        startActivity(intent, new Bundle());
    }

    void showNotifications() {
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent, new Bundle());
    }

    public void showMoreAdventures() {
        Intent intent = new Intent(this, AllAdventuresActivity.class);
        startActivity(intent, new Bundle());
    }

    public void showAchievementDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new MaterialStyledDialog.Builder(BaseActivity.this)
                        .setTitle("Получено достижение:\nМобильность")
                        .setDescription("Установить мобильное приложение")
                        .setIcon(R.drawable.going_mobile)
                        .withDialogAnimation(true)
                        //.withDialogAnimation(true, Duration.SLOW)
                        .show();
            }
        }, 3000);

    }


    public void showAdventure(Adventure adventure) {
        Intent intent = new Intent(this, AdventureActivity.class);
        intent.putExtra(ADVENTURE, Parcels.wrap(adventure));
        startActivity(intent);
    }


}
