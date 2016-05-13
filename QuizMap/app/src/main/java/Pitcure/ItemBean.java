package Pitcure;

import android.graphics.Bitmap;

/**
 * Created by Danieljet on 2016/5/10.
 * 用来存储很多小图片
 */
public class ItemBean {

       private int ItemId;   //小图片Id
       private int BitmapId; //所属图片Id
       private Bitmap  mbitmap; //小图片
       public  ItemBean(){

       }

    public ItemBean(int itemId, int bitmapId, Bitmap mbitmap) {
        ItemId = itemId;
        BitmapId = bitmapId;
        this.mbitmap = mbitmap;
    }

    public int getBitmapId() {
        return BitmapId;
    }

    public int getItemId() {
        return ItemId;
    }

    public Bitmap getMbitmap() {
        return mbitmap;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public void setBitmapId(int bitmapId) {
        BitmapId = bitmapId;
    }

    public void setMbitmap(Bitmap mbitmap) {
        this.mbitmap = mbitmap;
    }
}
