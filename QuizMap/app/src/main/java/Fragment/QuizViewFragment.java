package Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danieljet.quizmap.InitViewActivity;
import com.example.danieljet.quizmap.QuziViewActivity;
import com.example.danieljet.quizmap.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import Adapter.QuizGridViewAdapter;
import Data.CollectData;
import Pitcure.Genrate;
import Pitcure.ImagesUtil;
import Pitcure.ItemBean;
import Pitcure.PitcureUtils;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class QuizViewFragment extends Fragment implements AdapterView.OnItemClickListener {

      public   static int  position ;  //资源ID
      public   static int  level ;     //难度等级
      private   Bitmap  bitmap ;        //选择的拼图
      private   ImageView quizphoto;
      private   GridView  gridView1,gridView2,gridView3;
      private   QuizGridViewAdapter quizadapter;
      private  int  w;
      private  int  h;
      private  int  W;
      private  int  H;
      private BitmapDrawable  bitmapDrawable;
      private Bitmap bitmap1;
      private List<ItemBean> nullitembean = new ArrayList<ItemBean>();
      private int  itemid;    //原始的id相当于gridview中的position
      private int  bitmapid;  //交换后的id
      private  TextView  time;
      private TextView step;
      private int i = 0;    //时间秒数
      private int steps  = 0;    //步数
      private boolean flag  = true;  //判断线程
     //用来保存其它Fragment传过来的值
      public static  QuizViewFragment  newInstance(int position , int level){

            Bundle bundle = new Bundle();
            bundle.putInt("position" , position);
            bundle.putInt("level", level);
            QuizViewFragment fragment = new QuizViewFragment();
            fragment.setArguments(bundle);
            return  fragment;
      }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.quizview_activity,container,false);
          //获得Activity传过来的值
           position = getArguments().getInt("position");
           level = getArguments().getInt("level");
           bitmap = scale(position);              //bitmap在此有没有被做处理
           gridView1 = (GridView)view.findViewById(R.id.quiz_map2);
           gridView2 = (GridView)view.findViewById(R.id.quiz_map3);
           gridView3 = (GridView)view.findViewById(R.id.quiz_map4);
           time = (TextView)view.findViewById(R.id.time_show);
           step = (TextView)view.findViewById(R.id.step_show);
           step.setText("0");
           new Thread(new timethread()).start();
           //分割选中的图片(按分割的次数)
        if (level == 2) {
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView1.setAdapter(quizadapter);
            //添加点击事件
            gridView1.setOnItemClickListener(this);

        }
        if(level == 3){
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView2.setAdapter(quizadapter);
            //添加点击事件
            gridView2.setOnItemClickListener(this);
        }
        if(level == 4){
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView3.setAdapter(quizadapter);
            //添加点击事件
            gridView3.setOnItemClickListener(this);
        }

           return  view;
    }

    public Bitmap scale(int level){
          int i = level;
          int rid =  new CollectData().photos[i];
          Bitmap  bitmap = PitcureUtils.getScaledBitmap(getActivity(),rid);
          return bitmap;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(level == 2)
        {
            QuziViewActivity.itemBeanList.clear();   //把集合清空
            quizadapter = new QuizGridViewAdapter(getContext(),QuziViewActivity.itemBeanList);
            //把GridView刷新成空的
            gridView1.setAdapter(quizadapter);
            flag =false;
            steps = 0;
            Log.i("TAG","rrr");

        }else if(level == 3){
            QuziViewActivity.itemBeanList.clear();   //把集合清空
            quizadapter = new QuizGridViewAdapter(getContext(),QuziViewActivity.itemBeanList);
            //把GridView刷新成空的
            gridView2.setAdapter(quizadapter);
            flag =false;
            steps = 0;
            Log.i("TAG","rrr");
        }else{
            QuziViewActivity.itemBeanList.clear();   //把集合清空
            quizadapter = new QuizGridViewAdapter(getContext(),QuziViewActivity.itemBeanList);
            //把GridView刷新成空的
            gridView3.setAdapter(quizadapter);
            flag =false;
            steps = 0;
            Log.i("TAG","rrr");
        }
    }



    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Thread.interrupted();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           if(parent == gridView1)
           {
               initlevle(position,level);
           }
           else if(parent == gridView2) {

               initlevle(position,level);

           }else if(parent == gridView3){
               initlevle(position,level);
           }

    }


    //初始化小碎片，获得空白格的编号，只有按下空白格上下左右的图片才能进行图片的移动
     public void   initlevle(int position,int level) {

         itemid = position;
         bitmapid = QuziViewActivity.itemBeanList.get(position).getBitmapId();
         //如果bitmapid的值为8则说明点击的为空白块
         Log.i("TAg", itemid + "");
         Log.i("TAG", bitmapid + "");
         if (position + level <= (level * level -1)) {
             //下方有图片
             if (QuziViewActivity.itemBeanList.get(position + level).getBitmapId() == 0) {
                 new Genrate().swapItems(QuziViewActivity.itemBeanList.get(position),
                         QuziViewActivity.itemBeanList.get(position + level));
                 //进行交换,刷新界面
                 quizadapter.refresh(QuziViewActivity.itemBeanList);
                 steps  ++ ;
                 step.setText(steps +"");
             }
         }
         if (position - level >= 0) {
             //上方有图片
             if (QuziViewActivity.itemBeanList.get(position - level).getBitmapId() == 0) {
                 new Genrate().swapItems(QuziViewActivity.itemBeanList.get(position),
                         QuziViewActivity.itemBeanList.get(position - level));
                 //进行交换，刷新界面
                 quizadapter.refresh(QuziViewActivity.itemBeanList);
                 steps  ++ ;
                 step.setText(steps + "");
             }
         }
         if(position  % level != 0){
               //左方有图片
             if (QuziViewActivity.itemBeanList.get(position - 1).getBitmapId() == 0) {
                 new Genrate().swapItems(QuziViewActivity.itemBeanList.get(position),
                         QuziViewActivity.itemBeanList.get(position - 1));
                 //进行交换，刷新界面
                 quizadapter.refresh(QuziViewActivity.itemBeanList);
                 steps  ++ ;
                 step.setText(steps + "");
             }
         }  if( (position + 1) % level != 0){
             //右方有图片
             if (QuziViewActivity.itemBeanList.get(position + 1).getBitmapId() == 0) {
                 new Genrate().swapItems(QuziViewActivity.itemBeanList.get(position),
                         QuziViewActivity.itemBeanList.get(position + 1));
                 //进行交换，刷新界面
                 quizadapter.refresh(QuziViewActivity.itemBeanList);
                 steps  ++ ;
                 step.setText(steps +"");
             }
         }
     }

     public  class  timethread implements  Runnable{


         @Override
         public void run() {

             //当拼图拼成后，时间就不会再增长了
              while(flag){

                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           time.setText( i +"");
                       }
                   });
                  try {
                      Thread.sleep(1000);
                      i ++;
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
         }
     }

}
