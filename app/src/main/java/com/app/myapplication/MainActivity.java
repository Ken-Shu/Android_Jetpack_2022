package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

// 主程式
public class MainActivity extends AppCompatActivity {

    private int[] images;
    private Vibrator vibrator; // 震動

    // 程式進入點
    // Bundle 存放資料的集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 蒐集 images
        images = new int []{
                R.drawable.avatar_blond_female_girl_person_icon,
                R.drawable.avatar_boy_child_kid_male_icon,
                R.drawable.avatar_business_costume_male_man_icon,
                R.drawable.avatar_child_girl_kid_person_icon,
                R.drawable.avatar_female_grandmother_mature_old_icon,
                R.drawable.avatar_headset_male_man_person_icon,
                R.drawable.avatar_grandfather_man_mature_old_icon,
                R.drawable.avatar_female_headset_person_support_icon,
                R.drawable.avatar_male_man_mature_old_icon,
                R.drawable.avatar_male_man_person_user_icon,

        };
        // 建立震動物件
        vibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        // 配置UI畫面
        setContentView(R.layout.activity_main);
    }

    public void changeMyFace(View view){
        CircleImageView civ = (CircleImageView)view;
        civ.setImageResource(images[new Random().nextInt(images.length)]);
        vibrator.vibrate(100); // 震動 0.1 秒
//        vibrator.vibrate(5000); // 震動 5 秒
        //-1 會停 5000毫秒 在震動 2000毫秒 等下次按下才會再循環 表示不重複
        // 0 自動循環 停5000毫秒 震動 2000毫秒 不停重複
        // 1 起始停 5000毫秒 之後不停震動
        // vibrator.vibrate(new long[]{5000,2000}, 0);
    }
}