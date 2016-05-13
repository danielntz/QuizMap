package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.danieljet.quizmap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class GridViewAdapter  extends BaseAdapter{

     public List<BitmapDrawable> dataphoto  = new ArrayList<BitmapDrawable>();
     private Context       context;
     private LayoutInflater  inflater;
     private  ViewHolder  viewHolder;
     public  GridViewAdapter(){

     }
    public GridViewAdapter(Context context ,List<BitmapDrawable>hhh){
          this.context = context;
          this.inflater = LayoutInflater.from(context);
          this.dataphoto = hhh;
    }
    @Override
    public Object getItem(int position) {
        return dataphoto.get(position);
    }

    @Override
    public int getCount() {
        return dataphoto.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class  ViewHolder{
         private ImageView image;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
              viewHolder = new ViewHolder();
              convertView = inflater.inflate(R.layout.gridview_activity,null);
              viewHolder.image = (ImageView)convertView.findViewById(R.id.photo_image);
              convertView.setTag(viewHolder);
        }
       else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //返回数据
        viewHolder.image.setImageDrawable(dataphoto.get(position));

        return convertView;
    }
}
