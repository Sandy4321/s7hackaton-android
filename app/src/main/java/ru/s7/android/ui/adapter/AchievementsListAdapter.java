package ru.s7.android.ui.adapter;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.s7.android.R;
import ru.s7.android.model.Achievement;
import ru.s7.android.utils.time.DateUtil;

/**
 * The type Achievements selected list adapter.
 *
 * @author celikindv
 * @since 20/05/2017.
 */
public class AchievementsListAdapter extends RecyclerView.Adapter<AchievementsListAdapter.AchievementViewHolder> {

    private DateUtil dateUtil;
    private ArrayList<Achievement> mListAchievement;
    private OnAchievementListener mListener;
    private Context context;

    /**
     * Instantiates a new Achievement list adapter.
     */
    public AchievementsListAdapter(Context context) {
        this.mListAchievement = new ArrayList<>();
        this.context = context;
        this.dateUtil = new DateUtil();
        this.dateUtil.init(context);
    }

    /**
     * Set OnAchievementListener on RecyclerView layout_achievements to begin chat with a Achievement.
     *
     * @param mListener OnAchievementListener
     */
    public void setListener(OnAchievementListener mListener) {
        this.mListener = mListener;
    }

    /**
     * Sets list Achievement.
     *
     * @param mListAchievement Achievement list from db else network
     */
    public void setListAchievement(ArrayList<Achievement> mListAchievement) {
        this.mListAchievement = mListAchievement;
        notifyDataSetChanged();
    }

    /**
     * Gets  Achievements.
     *
     * @return the selected Achievements
     */
    public ArrayList<Achievement> getAchievements() {
        return mListAchievement;
    }

    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_card_achievements, parent, false);
        return new AchievementViewHolder(view, mListener, dateUtil);
    }

    @Override
    public void onBindViewHolder(AchievementViewHolder holder, int position) {
        Achievement Achievement = mListAchievement.get(position);
        holder.bind(Achievement);
    }

    @Override
    public void onViewRecycled(AchievementViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    @Override
    public int getItemCount() {
        return mListAchievement.size();
    }

    public Achievement getAchievement(int adapterPosition) {
        return mListAchievement.size() - 1 >= adapterPosition ?
                mListAchievement.get(adapterPosition) : null;
    }


    public interface OnAchievementListener {
        void onClick(int adapterPosition);
    }

    class AchievementViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.logo_achievements)
        CircleImageView logoAchievements;
        @BindView(R.id.labelTextView)
        TextView labelTextView;
        @BindView(R.id.descTextView)
        TextView descTextView;
        @BindView(R.id.timeTextView)
        TextView timeTextView;
        @BindView(R.id.progress_bar_achievements)
        ProgressBar progressBar;

        @BindView(R.id.layout_achievements)
        RelativeLayout layoutAchievements;

        OnAchievementListener listener;
        DateUtil dateUtil;

        public AchievementViewHolder(View itemView, OnAchievementListener listener, DateUtil dateUtil) {
            super(itemView);
            this.listener = listener;
            this.dateUtil = dateUtil;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_achievements)
        void onAchievementClick() {
            listener.onClick(getAdapterPosition());
        }

        public void clear() {
            logoAchievements.setImageDrawable(null);
            logoAchievements.clearColorFilter();
        }

        public void bind(Achievement achievement) {
            if (!TextUtils.isEmpty(achievement.getLogo()))
                Glide.with(logoAchievements.getContext())
                        .load(achievement.getLogo())
                        .error(R.drawable.ic_error_outline)
                        .into(logoAchievements);

            if (achievement.isUnlocked()) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                logoAchievements.setColorFilter(filter);
            }

            if (achievement.getCount() != achievement.getProgress()) {
                progressBar.setMax(achievement.getCount());
                progressBar.setProgress(achievement.getProgress());
                progressBar.setVisibility(achievement.getCount() != achievement.getProgress() ? View.VISIBLE : View.GONE);

            } else
                progressBar.setVisibility(View.GONE);

            labelTextView.setText(TextUtils.isEmpty(achievement.getLabel()) ? "" : achievement.getLabel());
            descTextView.setText(TextUtils.isEmpty(achievement.getDesc()) ? "" : achievement.getDesc());
            timeTextView.setText(dateUtil.formatDate(achievement.getTime()));

        }
    }

}
