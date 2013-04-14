package com.autismapplication.ViewTaskScreen;
 
import java.util.List;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
  
public class PageAdapter1 extends FragmentPagerAdapter {
  
    private List<NoteFragment> fragments;
    
    public PageAdapter1(FragmentManager fm, List<NoteFragment> fragments) {
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