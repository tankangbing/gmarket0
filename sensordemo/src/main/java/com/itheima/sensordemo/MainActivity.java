package com.itheima.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. 取得传感器的管理者
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //2. 取得手机中所有的传感器
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor: sensorList){
            Log.e("sensor",sensor.getName());
        }

        //3. 取得某个传感器
        //光线传感器  //取方向传感器
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //注册与注销 传感器   onCreate  onDestory
    }

    //onstart   onStop

    //onResume   // onPause


    @Override
    protected void onResume() {
        super.onResume();
        //注册  1. 监听器   2， 传感器   3， 采样率
        sensorManager.registerListener(listener,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //注销
        sensorManager.unregisterListener(listener);
    }

    //传感器事件监听
    private SensorEventListener listener=new SensorEventListener() {

        //数据发生改变
        @Override
        public void onSensorChanged(SensorEvent event) {
            //取得采集到的数据
            float[] values = event.values;
            Log.e("sensor","光线采样值-->"+values[0]);
        }
       //当精确度发生改变
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
