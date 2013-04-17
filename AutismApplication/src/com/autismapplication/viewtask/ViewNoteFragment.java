
package com.autismapplication.viewtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.autismapplication.R;

@SuppressLint("ValidFragment")
public class ViewNoteFragment extends Fragment {
    private TextView noteTitle;
    private TextView noteDescription;

    private String title;
    private String description;

    public ViewNoteFragment(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = (LinearLayout) inflater.inflate(R.layout.fragment_view_note, container, false);
        noteTitle = (TextView) rootView.findViewById(R.id.fragNoteTitle);
        noteTitle.setText(title);

        noteTitle = (TextView) rootView.findViewById(R.id.fragNoteDescription);
        noteTitle.setText(description);
        return rootView;
    }
}
