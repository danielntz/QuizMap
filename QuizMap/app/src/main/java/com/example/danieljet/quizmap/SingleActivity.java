package com.example.danieljet.quizmap;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public  abstract class SingleActivity extends FragmentActivity {

      private FragmentManager manager;
      private FragmentTransaction  transaction;

      public abstract Fragment createFrament();  //抽象方法


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.change_view);
        if(fragment == null){
            fragment = createFrament();
            manager.beginTransaction().add(R.id.change_view,fragment).commit();
        }

    }


}
