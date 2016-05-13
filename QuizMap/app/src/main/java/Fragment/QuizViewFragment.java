package Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.danieljet.quizmap.InitViewActivity;
import com.example.danieljet.quizmap.QuziViewActivity;
import com.example.danieljet.quizmap.R;

import java.math.BigDecimal;
import java.util.Random;

import Adapter.QuizGridViewAdapter;
import Data.CollectData;
import Pitcure.Genrate;
import Pitcure.ImagesUtil;
import Pitcure.PitcureUtils;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class QuizViewFragment extends Fragment{

      public   static int  position ;  //资源ID
      public   static int  level ;     //难度等级
      private   Bitmap  bitmap ;        //选择的拼图
      private   ImageView quizphoto;
      private   GridView  gridView1,gridView2,gridView3;
      private QuizGridViewAdapter quizadapter;
      private  int  w;
      private  int  h;
      private  int  W;
      private  int  H;
      private BitmapDrawable  bitmapDrawable;
      private Bitmap bitmap1;
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
           bitmap = scale(position);           //bitmap在此有没有被做处理
           gridView1 = (GridView)view.findViewById(R.id.quiz_map2);
           gridView2 = (GridView)view.findViewById(R.id.quiz_map3);
           gridView3 = (GridView)view.findViewById(R.id.quiz_map4);
           //分割选中的图片(按分割的次数)
        if (level == 2) {
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView1.setAdapter(quizadapter);
        }
        if(level == 3){
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView2.setAdapter(quizadapter);
        }
        if(level == 4){
            new ImagesUtil().createInitBitmaps(level, bitmap, getContext());
            //随机打乱顺序
            new Genrate().getPuzzleGenerator();
            quizadapter = new QuizGridViewAdapter(getContext(), QuziViewActivity.itemBeanList);
            gridView3.setAdapter(quizadapter);
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
        //释放图片资源
        quizadapter.shifang();
        Log.i("TAG","sdf");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
