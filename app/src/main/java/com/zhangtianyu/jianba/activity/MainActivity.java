package com.zhangtianyu.jianba.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhangtianyu.jianba.R;

/**
 * 三个按钮选项，点击进入各自的功能页面
 * 检查
 * 奖惩
 * 通报
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button jianchaBtn;
    private Button kaoheBtn;
    private Button tongbaoBtn;

    // 当Activity创建时，做一些初始化工作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到布局中的UI控件。三个按钮
        jianchaBtn = findViewById(R.id.jiancha_btn);
        kaoheBtn = findViewById(R.id.jiangcheng_btn);
        tongbaoBtn = findViewById(R.id.tongbao_btn);

        // 绑定点击事件监听器
        jianchaBtn.setOnClickListener(this);
        kaoheBtn.setOnClickListener(this);
        tongbaoBtn.setOnClickListener(this);
    }

    /**
     * 实现点击监听器的回调方法，这里的代码是被点击后的响应
     *
     * @param v 代表被点击的UI控件
     **/
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        // 通过被点击view的id区分是谁
        switch (v.getId()) {
            case R.id.jiancha_btn:
//                Intent intent = new Intent(this, JianChaActivity.class);
//                 启动新页面
//                this.startActivity(intent);

                // 设置跳转目标类
                intent.setClass(this, JianChaActivity.class);
                break;
            case R.id.jiangcheng_btn:
                break;
            case R.id.tongbao_btn:
                break;
        }
        this.startActivity(intent);
    }
}
