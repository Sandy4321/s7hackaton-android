package ru.s7.android.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.s7.android.R;
import ru.s7.android.model.Adventure;

/**
 * The type Adventures selected list adapter.
 *
 * @author celikindv
 * @since 20/05/2017.
 */
public class AdventuresListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Adventure> mListAdventure;
    private OnAdventureListener mListener;

    /**
     * Instantiates a new Adventure list adapter.
     */
    public AdventuresListAdapter(Context context) {
        this.mListAdventure = new ArrayList<>();
    }

    /**
     * Set OnAdventureListener on RecyclerView layout_adventures to begin chat with a Adventure.
     *
     * @param mListener OnAdventureListener
     */
    public void setListener(OnAdventureListener mListener) {
        this.mListener = mListener;
    }

    /**
     * Sets list Adventure.
     *
     * @param mListAdventure Adventure list from db else network
     */
    public void setListAdventure(ArrayList<Adventure> mListAdventure) {
        this.mListAdventure = mListAdventure;
        notifyDataSetChanged();
    }

    /**
     * Gets  Adventures.
     *
     * @return the selected Adventures
     */
    public ArrayList<Adventure> getAdventures() {
        return mListAdventure;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        View view = layoutInflater.inflate(R.layout.view_card_adventures, parent, false);
        viewHolder = new AdventureViewHolder(view, mListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Adventure Adventure = mListAdventure.get(position);
        ((AdventureViewHolder) holder).bind(Adventure);
    }

    @Override
    public int getItemCount() {
        return mListAdventure.size();
    }

    public Adventure getAdventure(int adapterPosition) {
        return mListAdventure.size() - 1 >= adapterPosition ?
                mListAdventure.get(adapterPosition) : null;
    }


    public interface OnAdventureListener {
        void onClick(int adapterPosition);
    }

    class AdventureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.logo_adventures)
        ImageView logoAdventures;
        @BindView(R.id.labelTextView)
        TextView labelTextView;
        @BindView(R.id.descTextView)
        TextView descTextView;
        @BindView(R.id.layout_adventures)
        CardView layoutAdventures;
        @BindView(R.id.rewardTextView)
        TextView rewardTextView;

        OnAdventureListener listener;

        public AdventureViewHolder(View itemView, OnAdventureListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_adventures)
        void onAdventureClick() {
            listener.onClick(getAdapterPosition());
        }

        public void bind(Adventure adventure) {
            if (!TextUtils.isEmpty(adventure.getLogo()))
                Glide.with(itemView.getContext()).load(adventure.getLogo()).error(R.drawable.ic_error_outline).into(logoAdventures);
            String reward = itemView.getContext().getString(R.string.info_reward_msg, adventure.getReward() > 0 ? adventure.getReward() : 0);
            rewardTextView.setText(reward);
            labelTextView.setText(TextUtils.isEmpty(adventure.getLabel()) ? "" : adventure.getLabel());
            descTextView.setText(TextUtils.isEmpty(adventure.getDesc()) ? "" : adventure.getDesc());

        }
    }

}
