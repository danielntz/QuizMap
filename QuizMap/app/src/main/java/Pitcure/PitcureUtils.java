package Pitcure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by Danieljet on 2016/5/10.
 * 图片处理类，将图片缩小至设备屏幕默认的大小,当图片过大时会造成内存泄漏out of memory
 * int rid  处理的图片的资源ID
 */
public class PitcureUtils  {
      private static Bitmap bitmap1;
     //获得BitmapDrawabel类型

     public  static BitmapDrawable getScaledDrawable(Activity a, int rid)
     {
         Display display = a.getWindowManager().getDefaultDisplay();
         float destWidth = display.getWidth();
         float destHeigth = display.getHeight();
         //read in the dimension of the image oh disk
         Log.i("TAG", destWidth +"" + destHeigth);   //屏幕的长宽高
         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds = true;// 如果值设为true，那么将不返回实际的bitmap，也不给其分配内存空间，这样就避免了内存溢出。
         //options是此图片的大小
         BitmapFactory.decodeResource(a.getResources(),rid,options);
         float srcWidth = options.outWidth;
         float srcHeight = options.outHeight;
         Log.i("TAG", srcWidth +"" + srcHeight);   //原图片的长宽高
         int insampleSize = 1;
         if(srcHeight > destHeigth || srcWidth > destWidth)
         {
             if(srcWidth > srcHeight){
                 insampleSize = Math.round(srcHeight / destHeigth);
                 Log.i("TAG",insampleSize +"");
             }
             else{
                 insampleSize = Math.round(srcWidth / destWidth);
                 Log.i("TAG",insampleSize +"");
             }
         }
         options = new BitmapFactory.Options();
         options.inSampleSize = insampleSize;
         Bitmap bitmap = BitmapFactory.decodeResource(a.getResources(),rid,options);
         Log.i("TAG",bitmap.getWidth() +  "" + bitmap.getHeight() );  //缩小后的图片大小(应该跟屏幕大小一样)
         return new BitmapDrawable(a.getResources(),bitmap);


     }
   //卸载图片
    public  static void cleanImageView(ImageView view){
         if(! (view.getDrawable() instanceof  BitmapDrawable))
          return;
         //清楚ImageView中的占用的内存
         BitmapDrawable b = (BitmapDrawable)view.getDrawable();
         b.getBitmap().recycle(); //清楚BitmapDrawable
         view.setImageDrawable(null);

    }

    //获得Bitmap类型
    public  static Bitmap getScaledBitmap(Activity a, int rid)
    {
        Display display = a.getWindowManager().getDefaultDisplay();
        float destWidth = display.getWidth();
        float destHeigth = display.getHeight();
        Log.i("TAG", destWidth +"" + destHeigth);   //屏幕的长宽高
        //read in the dimension of the image oh disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //options是此图片的大小
        BitmapFactory.decodeResource(a.getResources(),rid,options);
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        Log.i("TAG", srcWidth +"" + srcHeight);   //原图片的长宽高
        int insampleSize = 1;  //代表原图的缩小比例（宽高都缩小）
        if(srcHeight > destHeigth || srcWidth > destWidth)
        {
            if(srcWidth > srcHeight){
                insampleSize = Math.round(srcHeight / destHeigth);
                Log.i("TAG",insampleSize +"");
            }
            else{
                insampleSize = Math.round(srcWidth / destWidth);
                Log.i("TAG",insampleSize +"");
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = insampleSize;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(a.getResources(),rid,options);
        Log.i("TAG",options.outWidth +"" + options.outHeight);
        Log.i("TAG",bitmap.getWidth() +  "" + bitmap.getHeight() );  //缩小后的图片大小(应该跟屏幕大小一样)
        bitmap1 = Bitmap.createScaledBitmap(bitmap,480,800,true);
        Log.i("TAG",bitmap1.getWidth() +  "" + bitmap1.getHeight() );  //缩小后的图片大小(应该跟屏幕大小一样)
        return  bitmap1;


    }


}
