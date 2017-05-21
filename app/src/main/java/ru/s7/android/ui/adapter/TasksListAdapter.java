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
import ru.s7.android.model.Task;
import ru.s7.android.utils.time.DateUtil;

/**
 * The type Tasks selected list adapter.
 *
 * @author celikindv
 * @since 20/05/2017.
 */
public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.TaskViewHolder> {

    private DateUtil dateUtil;
    private ArrayList<Task> mListTask;
    private OnTaskListener mListener;
    private Context context;

    /**
     * Instantiates a new Task list adapter.
     */
    public TasksListAdapter(Context context) {
        this.mListTask = new ArrayList<>();
        this.context = context;
        this.dateUtil = new DateUtil();
        this.dateUtil.init(context);
    }

    /**
     * Set OnTaskListener on RecyclerView layout_Tasks to begin chat with a Task.
     *
     * @param mListener OnTaskListener
     */
    public void setListener(OnTaskListener mListener) {
        this.mListener = mListener;
    }

    /**
     * Sets list Task.
     *
     * @param tasks Task list from db else network
     */
    public void setListTasks(ArrayList<Task> tasks) {
        this.mListTask = tasks;
        notifyDataSetChanged();
    }

    /**
     * Gets  Tasks.
     *
     * @return the selected Tasks
     */
    public ArrayList<Task> getTasks() {
        return mListTask;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_card_task, parent, false);
        return new TaskViewHolder(view, mListener, dateUtil);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = mListTask.get(position);
        holder.bind(task);
    }

    @Override
    public void onViewRecycled(TasksListAdapter.TaskViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }


    @Override
    public int getItemCount() {
        return mListTask.size();
    }


    public Task getTask(int adapterPosition) {
        return mListTask.size() - 1 >= adapterPosition ?
                mListTask.get(adapterPosition) : null;
    }


    public interface OnTaskListener {
        void onClick(int adapterPosition);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.logo_tasks)
        CircleImageView logoTasks;
        @BindView(R.id.labelTextView)
        TextView labelTextView;
        @BindView(R.id.descTextView)
        TextView descTextView;
        @BindView(R.id.progress_bar_task)
        ProgressBar progressBar;
        @BindView(R.id.layout_tasks)
        RelativeLayout layoutTasks;

        OnTaskListener listener;
        DateUtil dateUtil;

        public TaskViewHolder(View itemView, OnTaskListener listener, DateUtil dateUtil) {
            super(itemView);
            this.listener = listener;
            this.dateUtil = dateUtil;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_tasks)
        void onTaskClick() {
            listener.onClick(getAdapterPosition());
        }

        public void clear() {
            logoTasks.setImageDrawable(null);
            logoTasks.clearColorFilter();
        }

        public void bind(Task task) {

            if (!task.isFinished()) {
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                logoTasks.setColorFilter(filter);
            }else
                clear();

            if (!TextUtils.isEmpty(task.getLogo()))
                Glide.with(itemView.getContext()).load(task.getLogo()).error(R.drawable.ic_error_outline).into(logoTasks);

            labelTextView.setText(TextUtils.isEmpty(task.getLabel()) ? "" : task.getLabel());
            descTextView.setText(TextUtils.isEmpty(task.getDesc()) ? "" : task.getDesc());

        }
    }

}
