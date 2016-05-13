package com.example.danieljet.quizmap;


import android.support.v4.app.Fragment;

import  Fragment.InitViewFragment;
/**
 * Created by Danieljet on 2016/5/9.
 */
public class InitViewActivity extends SingleActivity {

    @Override
    public Fragment createFrament() {
        return new InitViewFragment();
    }
}
