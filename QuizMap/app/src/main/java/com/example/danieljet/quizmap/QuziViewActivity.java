package com.example.danieljet.quizmap;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import Fragment.QuizViewFragment;
import Pitcure.ItemBean;

/**
 * Created by Danieljet on 2016/5/9.
 */
public class QuziViewActivity extends SingleActivity {

     public static List<ItemBean> itemBeanList = new ArrayList<ItemBean>();  //装载每一个小图片


    @Override
    public Fragment createFrament() {
         //获得传过来的值
        Bundle bundle = new Bundle();
        int position = this.getIntent().getExtras().getInt("position");
        int level  = this.getIntent().getExtras().getInt("level");
        return  QuizViewFragment.newInstance(position,level);  //把值传到QuziViewFragment中
    }



}

