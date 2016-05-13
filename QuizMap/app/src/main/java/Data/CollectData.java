package Data;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.danieljet.quizmap.R;

import java.util.ArrayList;
import java.util.List;

import Pitcure.PitcureUtils;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class CollectData  {

      public  List<Bitmap> photo = new ArrayList<Bitmap>();    //装载数据
      public  int photos [] = new int[]{R.drawable.one,R.drawable.two,R.drawable.three,
                                        R.drawable.four,R.drawable.five,R.drawable.six,
                                       };
      private  Bitmap bitmap[] = new Bitmap[photos.length];
      public CollectData(){
      }

}
