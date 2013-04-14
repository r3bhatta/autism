package com.autismapplication.ViewTaskScreen;
 
import java.util.List;
import java.util.Vector;
  
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
  
import com.autismapplication.R;
 
public class ImageFragmentActivity extends FragmentActivity{
     
    private PageAdapter1 mPagerAdapter;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.viewpager_layout);
  
        this.initialisePaging();
        //TODO: Add db call to get notes data needed.
         
    }
  
    private void initialisePaging() {
  
        List<NoteFragment> fragments = new Vector<NoteFragment>();
        NoteFragment fragment1 = new NoteFragment("Title1", "Lets cook some eggs.");  
        NoteFragment fragment2 = new NoteFragment("Title2", "This is what you do for step 1");
        NoteFragment fragment3 = new NoteFragment("Title3", "Now we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs" +
                "Now we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggsNow we really start to cook eggs");
         
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
  
        this.mPagerAdapter  = new PageAdapter1(super.getSupportFragmentManager(), fragments);
  
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
    }
}