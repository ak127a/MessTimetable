package com.akshatk.messtimetable;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Aboutfragment extends Fragment {

    public static Aboutfragment newInstance() {
        Aboutfragment fragment = new Aboutfragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutfragment, container, false);

        TextView emailtv = (TextView)view.findViewById(R.id.emailid);
        emailtv.setPaintFlags(emailtv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ak127a@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT  , "Feedback for mess timetable app");

        try {
            emailtv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        } catch (android.content.ActivityNotFoundException ex) {
            // handle edge case where no email client is installed
            Toast.makeText(getContext() , "Email app not found" , Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}