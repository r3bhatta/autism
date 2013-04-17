
package com.widgets;

import java.util.concurrent.atomic.AtomicBoolean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ToggleButton;

public class ToggleRemindersEnableButton extends ToggleButton {

    private AtomicBoolean on = new AtomicBoolean(false);

    @SuppressLint("Recycle")
    public ToggleRemindersEnableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void handleNewState(boolean newState) {

        if (newState) {
            setText("ON");
        } else {
            setText("OFF");
        }
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        OnClickListener wrappingListener = new OnClickListener() {

            public void onClick(View v) {
                boolean newState = !on.get();
                setState(newState);
                l.onClick(v);
            }
        };

        super.setOnClickListener(wrappingListener);
    }

    public void setState(boolean b) {
        on.set(b);
        handleNewState(b);
    }

}
