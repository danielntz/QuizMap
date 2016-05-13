package Pitcure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.danieljet.quizmap.QuziViewActivity;
import com.example.danieljet.quizmap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danieljet on 2016/5/10.
 */
public class ImagesUtil {

        public ItemBean itemBean;   //小图片
        public Bitmap   lastBitmap;
        public static ItemBean   lastblankbitmap ; //最后一块空白的图片
         /**
         * 切图，初始状态（正常顺序）
          * type   等级
          * bitmap 源图片
          * context 当前对象
         */

        public  void  createInitBitmaps(int type , Bitmap bitmap , Context context){
             Bitmap bitmapli = null;
             List<Bitmap> bitmapList = new ArrayList<Bitmap>();
             //每个Item的宽高
             int  itemWidth = bitmap.getWidth()/type;
             int  itemHeight = bitmap.getHeight()/type;
             for(int i = 1 ; i <= type ; i++){
                 for(int j =1 ; j <= type ; j++){
                     bitmapli = Bitmap.createBitmap(bitmap,(j-1)*itemWidth,(i-1)*itemHeight,
                               itemWidth,itemHeight);
                     //createBitmap函数
                     /*0 源图片
                      1 裁剪x方向的起始位置
                     * 2 裁剪y方向的起始位置
                     * 3 裁剪的宽度
                     * 4 裁剪的高度*/
                     bitmapList.add(bitmapli);
                     Log.i("TAG", bitmapli.getWidth() +"" + bitmapli.getHeight());
                     //编号用于N_Puzzle问题
                     itemBean = new ItemBean((i -1) * type + j ,(i -1) *type + j,bitmapli);
                     QuziViewActivity.itemBeanList.add(itemBean);
                 }
             }
            //保存最后一个图片在拼图完成时填充
            lastBitmap = bitmapList.get(type * type -1); //获得最后一个小图片
            //设置最后一个为空Item
            bitmapList.remove(type * type -1);
            QuziViewActivity.itemBeanList.remove(type * type  -1 );
            //把资源中的图片赋给blankbitmap
            Bitmap blankbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blank);
            //裁剪空白图片
            blankbitmap = Bitmap.createBitmap(blankbitmap,0,0,itemWidth,itemHeight);
            bitmapList.add(blankbitmap);   //把空白的图片加入到集合中
            QuziViewActivity.itemBeanList.add(new ItemBean(type * type , 0 ,blankbitmap));
            lastblankbitmap = QuziViewActivity.itemBeanList.get(type * type -1);
        }

     /*
      * 处理图片 放大 .缩小，使用矩阵
      * newWith 缩放后Width
      * newHeigh 缩放后Height
      * bitmap
      */

     public Bitmap resizeBitmap(float newWidth , float newHeigh ,Bitmap bitmap)
     {
         Matrix matrix = new Matrix();
         matrix.postScale(newWidth/bitmap.getWidth(),newHeigh/bitmap.getHeight());  //缩放函数
         Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
         return newBitmap;
     }




}
