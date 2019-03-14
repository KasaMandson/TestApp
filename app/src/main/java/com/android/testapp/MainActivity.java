package com.android.testapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.btn_write)
    public void writeFile() {
        save();
    }

    private void save() {
        FileOutputStream fos;
        BufferedWriter bw = null;
        try {
            fos = openFileOutput("test", Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("just fucking test!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @BindView(R.id.btn_read)
    Button btn_read;

    @OnClick(R.id.btn_read)
    public void readFile() {
        String sb = load();
        Toast.makeText(this, sb, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private String load() {
        FileInputStream fis;
        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();

        try {
            fis = openFileInput("test");
            br = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn_read.setTextColor(Color.RED);
    }
}
