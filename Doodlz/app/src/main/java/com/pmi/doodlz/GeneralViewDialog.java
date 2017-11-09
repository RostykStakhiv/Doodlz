import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

// the main screen that is painted
public class DoodleView extends View 
{
	 private static final float TOUCH_TOLERANCE = 10;

   private Bitmap bitmap; // drawing area for display or saving
   private Canvas bitmapCanvas; // used to draw on bitmap
   private Paint paintScreen; // use to draw bitmap onto screen
   private Paint paintLine; // used to draw lines onto bitmap
   private HashMap<Integer, Path> pathMap; // current Paths being drawn
   private HashMap<Integer, Point> previousPointMap; // current Points

   // DoodleView constructor initializes the DoodleView
   public DoodleView(Context context, AttributeSet attrs) 
   {
      super(context, attrs); // pass context to View's constructor

      paintScreen = new Paint(); // used to display bitmap onto screen

      // set the initial display settings for the painted line
      paintLine = new Paint();
      paintLine.setAntiAlias(true); // smooth edges of drawn line
      paintLine.setColor(Color.BLACK); // default color is black
      paintLine.setStyle(Paint.Style.STROKE); // solid line
      paintLine.setStrokeWidth(5); // set the default line width
      paintLine.setStrokeCap(Paint.Cap.ROUND); // rounded line ends
      pathMap = new HashMap<Integer, Path>();
      previousPointMap = new HashMap<Integer, Point>();
   } // end DoodleView constructor

   // Method onSizeChanged creates BitMap and Canvas after app displays
   @Override
   public void onSizeChanged(int w, int h, int oldW, int oldH)
   {
      bitmap = Bitmap.createBitmap(getWidth(), getHeight(), 
         Bitmap.Config.ARGB_8888);
      bitmapCanvas = new Canvas(bitmap);
      bitmap.eraseColor(Color.WHITE); // erase the BitMap with white
   } // end method onSizeChanged
   
   // clear the painting
   public void clear()
   {
      pathMap.clear(); // remove all paths
      previousPointMap.clear(); // remove all previous points
      bitmap.eraseColor(Color.WHITE); // clear the bitmap 
      invalidate(); // refresh the screen
   } // end method clear
};