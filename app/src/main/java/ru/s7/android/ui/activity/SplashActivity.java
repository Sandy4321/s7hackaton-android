package ru.s7.android.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.s7.android.R;
import ru.s7.android.ui.mvp.view.ASplashMvpView;
import ru.s7.android.utils.Constants;

/**
 * The type Splash activity.
 *
 * @author celikindv
 * @since 21.05.16.  <p> This is SplashActivity.
 */
public class SplashActivity extends BaseActivity implements ASplashMvpView {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);
        startWithDelay();
    }


    private void startWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showAchievements();
            }
        }, Constants.SPLASH_DISPLAY_LENGTH);
    }
}