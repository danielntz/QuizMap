package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.danieljet.quizmap.R;

import java.util.ArrayList;
import java.util.List;

import Pitcure.ImagesUtil;
import Pitcure.ItemBean;
import Pitcure.PitcureUtils;

/**
 * Created by Danieljet on 2016/5/10.
 */
public class QuizGridViewAdapter extends BaseAdapter {

      private Context context;
      private LayoutInflater inflater;
      private  List<ItemBean> smallphoto = new ArrayList<ItemBean>();
      private  ViewHolder  viewHolder;
      private  int   clean = 0 ;  //用来做判断是否清楚bitmap资源的标志
     static  class  ViewHolder{

          private ImageView  smallphotoshow;
    }
     public  QuizGridViewAdapter(){

    }

    public  QuizGridViewAdapter (Context context ,List<ItemBean> hhh){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.smallphoto  = hhh;
    }

    public int getCount() {
        return smallphoto.size();
    }

    @Override
    public Object getItem(int position) {
        return smallphoto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  void  shifang(){
         clean = 1;
        notifyDataSetChanged();   //重新调用getView方法

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         if(convertView == null){
             viewHolder  = new ViewHolder();
             convertView = inflater.inflate(R.layout.smallgridview_activity,null);
             viewHolder.smallphotoshow = (ImageView)convertView.findViewById(R.id.smallphoto);
             convertView.setTag(viewHolder);
         }
        else{
              viewHolder =  (ViewHolder) convertView.getTag();
         }
        //返回数据
        if(clean == 0) {
            //添加bitmap数据
            viewHolder.smallphotoshow.setImageBitmap(smallphoto.get(position).getMbitmap());
        }
        else{
            //清楚bitmap数据
            PitcureUtils.cleanImageView(viewHolder.smallphotoshow);
            clean = 0;
            Log.i("TAG","uuuu");
        }
        Log.i("TAG", smallphoto.get(position).getMbitmap().getWidth() + "" +smallphoto.get(position).getMbitmap().getHeight());
        return convertView;
    }
}
