package Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.danieljet.quizmap.InitViewActivity;
import com.example.danieljet.quizmap.QuziViewActivity;
import com.example.danieljet.quizmap.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.GridViewAdapter;
import Data.CollectData;
import Pitcure.PitcureUtils;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class InitViewFragment  extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{


    private static final String TAG = null ;
      private TextView     level_choose;
      private PopupWindow   window;
      private GridViewAdapter  grid_adapter;
      private CollectData  data ;
      private GridView  grid_view;
      private List<BitmapDrawable> photo_data = new ArrayList<BitmapDrawable>();
      private int  level  = 3 ;          //难度等级
      private  View  viewdialog  ;              //用户自定义对话框控件
      private  TextView   one,two,three;
      private  Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.initview_activity,container,false);
         //初始化自定义对话框
         viewdialog = inflater.inflate(R.layout.level_activity,(ViewGroup)view.findViewById(R.id.level_select));
         one =  (TextView)viewdialog.findViewById(R.id.small);
         two =  (TextView)viewdialog.findViewById(R.id.zhong);
         three = (TextView)viewdialog.findViewById(R.id.high);
         level_choose = (TextView)view.findViewById(R.id.level_choose);
         grid_view = (GridView)view.findViewById(R.id.grid_view);
         level_choose.setOnClickListener(this);
         grid_view.setOnItemClickListener(this);
         one.setOnClickListener(this);
         two.setOnClickListener(this);
         three.setOnClickListener(this);
         init();
         return view;
    }
    //初始装载的图片
    public void init(){
         Log.i("TAG", "sdfsd");
         data = new CollectData();
        // data.init(getActivity());
        for(int i = 0  ; i < data.photos.length; i ++){
            photo_data.add(PitcureUtils.getScaledDrawable(getActivity(),data.photos[i]));
        }
         grid_adapter = new GridViewAdapter(getActivity(),photo_data);
         grid_view.setAdapter(grid_adapter);

  }
    //点击事件
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.level_choose:

                //弹出选择难度的对话框,自定义对话框中的变量
                  ViewGroup  viewGroup = (ViewGroup)viewdialog.getParent();
                if(viewGroup != null){
                    viewGroup.removeAllViewsInLayout();
                }
               dialog = new AlertDialog.Builder(getContext())
                                  .setView(viewdialog).create();
                dialog.show();
                break;
           case R.id.small :
                level = 2;
                dialog.dismiss();
                break;
           case R.id.zhong:
                level = 3;
                dialog.dismiss();
                break;
           case R.id.high:
                level = 4;
                dialog.dismiss();
                break;
       }
    }
    //点击gridview事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(parent == grid_view ){
                Bundle bundle  = new Bundle();
                bundle.putInt("position",position);  //图片资源id
                Log.i("TAG",position + "");
                bundle.putInt("level",level);         //难度等级
                Intent  intent  = new Intent(getContext(),QuziViewActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);   //弹出拼图界面
                //判断最后一幅图，采用隐式Intent,从相册或者是照相机中获得图片


            }
    }

}
