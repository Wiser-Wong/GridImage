package com.wiser.grdimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wiser.grid.grid.PhotoGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PhotoGridView pgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgv = findViewById(R.id.pgv);

        pgv.setOnPhotoGridListener(new PhotoGridView.OnPhotoGridListener() {
            @Override
            public void onAddClick(View view, int position) {
                Toast.makeText(MainActivity.this,"添加",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"点击",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDeleteClick(View view, int position) {
                Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_LONG).show();
                pgv.delete(position);
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018557288,1217151095&fm=26&gp=0.jpg");
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018557288,1217151095&fm=26&gp=0.jpg");
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018557288,1217151095&fm=26&gp=0.jpg");
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018557288,1217151095&fm=26&gp=0.jpg");
        list.add("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018557288,1217151095&fm=26&gp=0.jpg");
        pgv.setPhotoData(list);
    }
}
