
package com.autismapplication.viewtask;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.autismapplication.R;

public class ViewNoteFragmentActivity extends FragmentActivity {

    private NoteAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.fragment_activity_viewpager_layout);

        this.initialisePaging();
        // TODO: Add db call to get notes data needed.

    }

    private void initialisePaging() {

        List<ViewNoteFragment> fragments = new Vector<ViewNoteFragment>();
        ViewNoteFragment fragment1 = new ViewNoteFragment("Title1", "Lets cook some eggs.");
        ViewNoteFragment fragment2 = new ViewNoteFragment("Title2",
                "This is what you do for step 1");
        ViewNoteFragment fragment3 = new ViewNoteFragment("Title3", "Now we cook eggs");

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        this.mPagerAdapter = new NoteAdapter(super.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }

    private class NoteAdapter extends FragmentPagerAdapter {
        private List<ViewNoteFragment> fragments;

        public NoteAdapter(FragmentManager fm, List<ViewNoteFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

}
