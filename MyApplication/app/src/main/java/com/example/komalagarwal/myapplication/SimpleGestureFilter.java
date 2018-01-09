package com.example.komalagarwal.myapplication;

import android.app.Activity;
import android.view.GestureDetector;

/**
 * Created by komalagarwal on 5/25/2016.
 */
public class SimpleGestureFilter extends GestureDetector.SimpleOnGestureListener {
    public final static int SWIPE_UP=1;
    public final static int SWIPE_DOWN=2;
    public final static int SWIPE_LEFT=3;
    public final static int SWIPE_RIGHT=4;

    public final static int MODE_TRANSPARENT=0;
    public final static int MODE_SOLID=1;
    public final static int MODE_DYNAMIC=2;
    private final static int ACTION_FAKE=-13;

    private int swipe_Min_Distance= 100;
    private int swipe_Max_Distance=350;
    private int swipe_Min_Velocity=100;

    private int mode =MODE_DYNAMIC;
    private boolean running =true;
    private boolean tapIndicator=false;

    private Activity context;
    private GestureDetector detector;
  /*  private SimpleGestureListener listener;
    public SimpleGestureFilter(Activity context,SimpleGestureListener sgl)
    {

        this.context=context;
        this.detector=new GestureDetector(context,this);
        this.listener=sgl;
    }
*/
}