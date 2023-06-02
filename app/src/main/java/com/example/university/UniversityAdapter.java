package com.example.university;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UniversityAdapter extends ArrayAdapter<University> {
    private Context context;

    public UniversityAdapter(Context context, List<University> universities) {
        super(context, 0, universities);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_university, parent, false);
        }

        University currentUniversity = getItem(position);

        ImageView logoImageView = convertView.findViewById(R.id.imageview_logo);
        TextView nameTextView = convertView.findViewById(R.id.textview_name);

        logoImageView.setImageResource(currentUniversity.getLogoResId());
        nameTextView.setText(currentUniversity.getName());

        return convertView;
    }
}
