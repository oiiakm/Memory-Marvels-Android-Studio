package com.example.memory_marvels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Card> cards;

    public CardAdapter(Context context, ArrayList<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, null);
        } else {
            view = convertView;
        }

        Card card = cards.get(position);
        ImageView imageView = view.findViewById(R.id.image_view);
        if (card.isFlipped()) {
            imageView.setImageResource(card.getImageId());
        } else {
            imageView.setImageResource(R.drawable.logo);
        }
        return view;
    }
}
