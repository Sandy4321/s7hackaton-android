package ru.s7.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.s7.android.R;
import ru.s7.android.model.NotificationPush;
import ru.s7.android.utils.time.DateUtil;

/**
 * The type Notifications selected list adapter.
 *
 * @author celikindv
 * @since 20/05/2017.
 */
public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.NotificationViewHolder> {

    private DateUtil dateUtil;
    private ArrayList<NotificationPush> mListNotification;
    private OnNotificationListener mListener;
    private Context context;

    /**
     * Instantiates a new Notification list adapter.
     */
    public NotificationsListAdapter(Context context) {
        this.mListNotification = new ArrayList<>();
        this.context = context;
        this.dateUtil = new DateUtil();
        this.dateUtil.init(context);
    }

    /**
     * Set OnNotificationListener on RecyclerView layout_notifications to begin chat with a Notification.
     *
     * @param mListener OnNotificationListener
     */
    public void setListener(OnNotificationListener mListener) {
        this.mListener = mListener;
    }

    /**
     * Sets list Notification.
     *
     * @param mListNotification Notification list from db else network
     */
    public void setListNotification(ArrayList<NotificationPush> mListNotification) {
        this.mListNotification = mListNotification;
        notifyDataSetChanged();
    }

    /**
     * Gets  Notifications.
     *
     * @return the selected Notifications
     */
    public ArrayList<NotificationPush> getNotificationsPush() {
        return mListNotification;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_card_notifications, parent, false);
        return new NotificationViewHolder(view, mListener, dateUtil);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        NotificationPush notificationPush = mListNotification.get(position);
        holder.bind(notificationPush);
    }

    @Override
    public void onViewRecycled(NotificationViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    @Override
    public int getItemCount() {
        return mListNotification.size();
    }

    public NotificationPush getNotificationPush(int adapterPosition) {
        return mListNotification.size() - 1 >= adapterPosition ?
                mListNotification.get(adapterPosition) : null;
    }


    public interface OnNotificationListener {
        void onClick(int adapterPosition);
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.labelTextView)
        TextView labelTextView;
        @BindView(R.id.descTextView)
        TextView descTextView;
        @BindView(R.id.timeTextView)
        TextView timeTextView;
        @BindView(R.id.scoreTextView)
        TextView scoreTextView;
        @BindView(R.id.layout_notifications)
        RelativeLayout layoutNotifications;

        OnNotificationListener listener;
        DateUtil dateUtil;

        public NotificationViewHolder(View itemView, OnNotificationListener listener, DateUtil dateUtil) {
            super(itemView);
            this.listener = listener;
            this.dateUtil = dateUtil;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_notifications)
        void onNotificationClick() {
            listener.onClick(getAdapterPosition());
        }

        public void clear() {

        }

        public void bind(NotificationPush notification) {
            scoreTextView.setText("+500 Баллов");
            labelTextView.setText(TextUtils.isEmpty(notification.getLabel()) ? "" : notification.getLabel());
            descTextView.setText(TextUtils.isEmpty(notification.getDesc()) ? "" : notification.getDesc());
            timeTextView.setText(dateUtil.formatDate(notification.getTime()));

        }
    }

}
