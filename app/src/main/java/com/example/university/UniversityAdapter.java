package com.example.university;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UniversityAdapter extends ArrayAdapter<University> {
    private final Context context;

    public UniversityAdapter(Context context, List<University> universities) {
        super(context, 0, universities);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_university, parent, false);
        }

        ImageView imageViewLogo = convertView.findViewById(R.id.imageview_logo);
        TextView textViewName = convertView.findViewById(R.id.textview_name);

        University university = getItem(position);
        if (university != null) {
            String logoFileName = university.getLogoFileName();
            @SuppressLint("DiscouragedApi") int logoResId = context.getResources().getIdentifier(logoFileName, "drawable", context.getPackageName());
            imageViewLogo.setImageResource(logoResId);
            textViewName.setText(university.getName());
        }

        return convertView;
    }
}
