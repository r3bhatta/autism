package com.autismapplication.ViewTaskScreen;
 
import java.util.ArrayList;
import java.util.List;
 
import com.autismapplication.ActivitySwipeDetector;
import com.autismapplication.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class ViewNoteScreen extends Activity {
 
    private Button closeButton;
    private List<List<String>> notesData;
    private int currentNoteIndex = 1;
    private int numNotes;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_note_screen);
        CreateData();
        numNotes = notesData.size();
         
        TextView currentIndex = (TextView) findViewById(R.id.currentIndex);
        currentIndex.setText(currentNoteIndex + " / " + notesData.size());
         
        TextView noteTitle = (TextView) findViewById(R.id.taskTitle);
        noteTitle.setText(notesData.get(0).get(0));
         
        TextView noteDescription = (TextView) findViewById(R.id.taskDescription);
        noteDescription.setText(notesData.get(0).get(1));
         
        ActivitySwipeDetector swipeDetector = new ActivitySwipeDetector(this);
        LinearLayout baseLayout = (LinearLayout) this.findViewById(R.id.BaseLayout);
        baseLayout.setOnTouchListener(swipeDetector);
         
        closeButton = (Button) findViewById(R.id.CloseButton);
         
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(ViewNoteScreen.this, ViewTask.class);
                startActivity(i);
            }
        });
    }
     
    private void CreateData(){
        notesData = new ArrayList<List<String>>();
         
        for(int i = 0; i < 6 ; i++){
            List<String> note = new ArrayList<String>();
            String noteTitle = "Cook Eggs" + (i+1);
            String noteDescription = (i+1) + "This task is about cooking eggs. First turn on the stove. Then put the pan on top. Add oil and let heat for 2 mins. In a bowl, crack eggs and add some salt, pepper. Put eggs in pan and cook for 5 mins. ";
            note.add(noteTitle);
            note.add(noteDescription);
            notesData.add(note);
        }
    }
     
    public void onRightToLeftSwipe(){
        if(currentNoteIndex < notesData.size()){
            currentNoteIndex++;
            TextView noteTitle = (TextView) findViewById(R.id.taskTitle);
            noteTitle.setText(notesData.get(currentNoteIndex - 1).get(0));
             
            TextView noteDescription = (TextView) findViewById(R.id.taskDescription);
            noteDescription.setText(notesData.get(currentNoteIndex - 1).get(1));
             
            TextView currentIndex = (TextView) findViewById(R.id.currentIndex);
            currentIndex.setText(currentNoteIndex + " / " + notesData.size());
        }
    }
     
    public void onLeftToRightSwipe(){
        if(currentNoteIndex > 1){
            currentNoteIndex--;
            TextView noteTitle = (TextView) findViewById(R.id.taskTitle);
            noteTitle.setText(notesData.get(currentNoteIndex - 1).get(0));
             
            TextView noteDescription = (TextView) findViewById(R.id.taskDescription);
            noteDescription.setText(notesData.get(currentNoteIndex - 1).get(1));
             
            TextView currentIndex = (TextView) findViewById(R.id.currentIndex);
            currentIndex.setText(currentNoteIndex + " / " + notesData.size());
        }
    }
}