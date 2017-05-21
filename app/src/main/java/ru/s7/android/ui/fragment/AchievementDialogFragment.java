package ru.s7.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.s7.android.R;
import ru.s7.android.model.Achievement;
import ru.s7.android.ui.mvp.view.FAchievementDialogMvpView;

/**
 * Created by celikindv on 21.05.17.
 * <p>
 * This fragment responsible for show achievement
 */
public class AchievementDialogFragment extends android.support.v4.app.DialogFragment implements FAchievementDialogMvpView {


    private TextView descTextView;
    private TextView labelTextView;
    private Button cancelBtn;
    private ImageView logoAchievement;
    private Achievement achievement;
    private ProgressBar progressBarAchievement;
    FragmentManager fragmentManager;

    /**
     * @param manager the manager
     * @return the bottom sheet attach fragment
     */
    public static AchievementDialogFragment with(FragmentManager manager) {
        AchievementDialogFragment fragment = new AchievementDialogFragment();
        fragment.fragmentManager = manager;
        return fragment;
    }

    public AchievementDialogFragment achievement(Achievement achievement) {
        this.achievement = achievement;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        show(fragmentManager, getTag());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setRetainInstance(true);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(R.string.my_achievement_title);
        View v = inflater.inflate(R.layout.achievement_dialog, container, false);
        descTextView = (TextView) v.findViewById(R.id.descTextView);
        labelTextView = (TextView) v.findViewById(R.id.labelTextView);
        cancelBtn = (Button) v.findViewById(R.id.achievements_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        logoAchievement = (ImageView) v.findViewById(R.id.logoAchievement);
        progressBarAchievement = (ProgressBar) v.findViewById(R.id.progress_bar_achievement);
        labelTextView.setText(TextUtils.isEmpty(achievement.getLabel()) ? "" : achievement.getLabel());
        descTextView.setText(TextUtils.isEmpty(achievement.getDesc()) ? "" : achievement.getDesc());

        if (achievement.getCount() != achievement.getProgress()) {
            progressBarAchievement.setMax(achievement.getCount());
            progressBarAchievement.setProgress(achievement.getProgress());
            progressBarAchievement.setVisibility(achievement.getCount() != achievement.getProgress() ? View.VISIBLE : View.GONE);
        } else
            progressBarAchievement.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(achievement.getLogo()))
            Glide.with(logoAchievement.getContext())
                    .load(achievement.getLogo())
                    .error(R.drawable.ic_error_outline)
                    .into(logoAchievement);

        return v;
    }

}
