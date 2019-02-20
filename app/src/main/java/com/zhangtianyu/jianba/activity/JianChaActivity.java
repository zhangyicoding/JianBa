package com.zhangtianyu.jianba.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangtianyu.jianba.R;
import com.zhangtianyu.jianba.entity.XiangDianEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查
 */
public class JianChaActivity extends AppCompatActivity implements View.OnClickListener {

    // 项点按钮测试数据
    private String[] items = new String[]{
            "早上好",
            "中午好",
            "晚上好"
    };

    private boolean[] isCheckArray = new boolean[items.length];

    protected EditText jiancharenEt;
    protected EditText shoujiandanweiEt;
    protected Button xiangdianBtn;
    protected ListView yixuanLv;
    protected Button baocunBtn;

    private XiangDianAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_cha);
        initView();
    }

    // 初始化视图
    private void initView() {
        jiancharenEt = (EditText) findViewById(R.id.jiancharen_et);
        shoujiandanweiEt = (EditText) findViewById(R.id.shoujiandanwei_et);
        xiangdianBtn = (Button) findViewById(R.id.xiangdian_btn);
        xiangdianBtn.setOnClickListener(JianChaActivity.this);
        yixuanLv = (ListView) findViewById(R.id.yixuan_lv);
        baocunBtn = (Button) findViewById(R.id.baocun_btn);
        baocunBtn.setOnClickListener(JianChaActivity.this);

        adapter = new XiangDianAdapter();
        yixuanLv.setAdapter(adapter);// ListView绑定适配器
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.xiangdian_btn) {
            showXiangDianDialog();
        } else if (view.getId() == R.id.baocun_btn) {

        }
    }

    // 点击项点弹出选项弹窗
    private void showXiangDianDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 对话框item多选监听器
        builder.setMultiChoiceItems(items, isCheckArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // 如果某个item被选中，则加入适配器
                // 如果某个item取消选中，从适配器中移除
                if (isChecked) {
                    adapter.addItem(items[which]);
                } else {
                    adapter.removeItem(which);
                }
            }
            // 对话框确定监听器
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int itemSize = adapter.list.size();
                if (itemSize < 1) {
                    Toast.makeText(JianChaActivity.this, "请至少选择一个", Toast.LENGTH_LONG).show();
                } else if (itemSize > 10) {
                    Toast.makeText(JianChaActivity.this, "最多只能选10个", Toast.LENGTH_LONG).show();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        })
                // 对话框取消监听器
                .setNegativeButton("取消", null)
                .show();// 展示对话框
    }

    // 已选项点适配器
    private static class XiangDianAdapter extends BaseAdapter {

        // 展示的数据
        private List<XiangDianEntity> list;

        public XiangDianAdapter() {
            list = new ArrayList<>();
        }

        // 添加新项点
        private void addItem(String item) {
            XiangDianEntity entity = new XiangDianEntity();
            entity.a = item;
            list.add(entity);
        }

        // 移除项点
        private void removeItem(int index) {
            list.remove(index);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public XiangDianEntity getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_xiangdian, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = ((ViewHolder) convertView.getTag());
            }
            holder.bind(getItem(position).a);
            return convertView;
        }

        private static class ViewHolder {

            private TextView aTextView;

            public ViewHolder(View itemView) {
                aTextView = itemView.findViewById(R.id.a_tv);
            }

            // 绑定数据
            void bind(String item) {
                aTextView.setText(item);
            }
        }
    }
}
