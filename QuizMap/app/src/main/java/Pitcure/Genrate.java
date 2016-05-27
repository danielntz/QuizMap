package Pitcure;

import android.util.Log;

import com.example.danieljet.quizmap.QuziViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Fragment.QuizViewFragment;

/**
 * Created by Danieljet on 2016/5/10.
 */
public class  Genrate   {

      private List<Integer> data = new ArrayList<Integer>();  //装载小图片的ID编号，用来形成算法需要的序列
     /**
     * 生成拼图的界面
     */
    public  void getPuzzleGenerator() {
        int index = 0;
        //随机打乱顺序
        Random random = new Random();
        for (int i = 0; i < QuziViewActivity.itemBeanList.size(); i++) {
            index = (random.nextInt(QuizViewFragment.level * QuizViewFragment.level));
            //交换小图片的ID号
             swapItems(QuziViewActivity.itemBeanList.get(index),ImagesUtil.lastblankbitmap);
        }
        //把打乱的顺序装载到序列中
        for(int i = 0 ; i < QuziViewActivity.itemBeanList.size(); i ++){
                data.add(QuziViewActivity.itemBeanList.get(i).getBitmapId());
        }
        //判断生成的无序图片是否有解
        if(canSolve(data)){
            Log.i("TAG","分割成功");

        }
        else{
            getPuzzleGenerator();
        }
    }

    //生成无序的序列
    public  void swapItems(ItemBean from , ItemBean blank){

        ItemBean newItemBean = new ItemBean();
        //交换BitmapId
        newItemBean.setBitmapId(from.getBitmapId());
        from.setBitmapId(blank.getBitmapId());
        blank.setBitmapId(newItemBean.getBitmapId());
        //交换Bitmap
        newItemBean.setMbitmap(from.getMbitmap());
        from.setMbitmap(blank.getMbitmap());
        blank.setMbitmap(newItemBean.getMbitmap());
        //设置新的Blank
        ImagesUtil.lastblankbitmap = from;
    }
    //判断该数据是否有解
    public  boolean canSolve(List<Integer> data){
        //获取空白图片ID
        int blank = ImagesUtil.lastblankbitmap.getItemId();
        //可行性原则
        if(data.size() %2 ==1 ){
            return getInversion(data) % 2 == 0;
        }
        else{
            //从上往下数，空格位于奇数行
            if( ((blank - 1) / QuizViewFragment.level)%2 == 1 ){
                return getInversion(data)%2 == 0;
            }
            else{
                //从下往上数，空位位于偶数行
                return getInversion(data)%2 == 1;
            }
        }
    }

    //计算倒置和算法
    public int getInversion(List<Integer> data){
        int inversion = 0;
        int inversionCount = 0;
        for(int i =0 ; i < data.size() ; i++){
            for(int j = i +1;j < data.size() ; j++){
                int index = data.get(i);
                if(data.get(j) != 0 && data.get(j) <index){
                    inversionCount ++;
                }
            }
            inversion += inversionCount;
            inversionCount = 0;
        }
        return inversion;
    }

}
