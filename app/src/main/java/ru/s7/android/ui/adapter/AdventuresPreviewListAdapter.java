package ru.s7.android.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
public class AdventuresPreviewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM = 0;
    public static final int ITEM_MORE = 1;

    private ArrayList<Adventure> mListAdventure;
    private OnAdventureListener mListener;

    /**
     * Instantiates a new Adventure list adapter.
     */
    public AdventuresPreviewListAdapter() {
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
        this.mListAdventure.add(null);
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
        View view = null;
        switch (viewType) {
            case ITEM:
                view = layoutInflater.inflate(R.layout.view_card_adventures_preview, parent, false);
                viewHolder = new AdventureViewHolder(view, mListener);
                break;
            case ITEM_MORE:
                view = layoutInflater.inflate(R.layout.view_card_adventures_more, parent, false);
                viewHolder = new AdventureMoreViewHolder(view, mListener);
                break;
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (mListAdventure.size() - 1 == position) {
            return ITEM_MORE;
        } else {
            return ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Adventure Adventure = mListAdventure.get(position);
        if (holder instanceof AdventureViewHolder) {
            ((AdventureViewHolder) holder).bind(Adventure);
        }
        if (holder instanceof AdventureMoreViewHolder) {
            // TODO: 20/05/2017
        }

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

        void onClickMore();

        void onClickShare(int adapterPosition);
    }

    class AdventureMoreViewHolder extends RecyclerView.ViewHolder {

        OnAdventureListener listener;
        @BindView(R.id.labelTextView)
        TextView labelTextView;
        @BindView(R.id.layout_adventures)
        CardView layoutAdventures;

        public AdventureMoreViewHolder(View itemView, OnAdventureListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_adventures)
        void onAdventureClick() {
            listener.onClickMore();
        }
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
        @BindView(R.id.moreButton)
        ImageButton moreButton;

        OnAdventureListener listener;

        public AdventureViewHolder(View itemView, OnAdventureListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.moreButton)
        void onMoreButton() {
            listener.onClickShare(getAdapterPosition());
        }

        @OnClick(R.id.layout_adventures)
        void onAdventureClick() {
            listener.onClick(getAdapterPosition());
        }

        public void bind(Adventure adventure) {
            if (!TextUtils.isEmpty(adventure.getLogo()))
                Glide.with(itemView.getContext()).load(adventure.getLogo()).error(R.drawable.ic_error_outline).into(logoAdventures);

            labelTextView.setText(TextUtils.isEmpty(adventure.getLabel()) ? "" : adventure.getLabel());
            descTextView.setText(TextUtils.isEmpty(adventure.getDesc()) ? "" : adventure.getDesc());

        }
    }

}
