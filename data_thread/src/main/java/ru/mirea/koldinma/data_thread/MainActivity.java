package ru.mirea.koldinma.data_thread;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    List<String> aboutMethods;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        aboutMethods = new ArrayList<>();




        mHandler.postDelayed(() -> aboutMethods.add("postDelayed - приводит к добавлению выполняемого объекта в очередь сообщений," +
                " который будет запущен по истечении указанного промежутка времени. Запускаемый будет выполняться " +
                "в потоке пользовательского интерфейса."),300);

        mHandler.post(() -> aboutMethods.add("post - приводит к добавлению выполняемого сообщения в очередь сообщений." +
                " Запускаемый будет выполняться в потоке пользовательского интерфейса."));
        runOnUiThread(() -> aboutMethods.add("runOnUiThread - Запускает указанное действие в потоке пользовательского интерфейса." +
                " Если текущий поток является потоком пользовательского интерфейса, то действие" +
                " выполняется немедленно. Если текущий поток не является потоком пользовательского" +
                " интерфейса, действие публикуется в очереди событий потока пользовательского интерфейса"));
    }


    public void Click(View view)
    {
        while (true)
        {
            if (aboutMethods.size() == 3) {
                textView.setText("Первый:\n\n" + aboutMethods.get(0) + "\n\nВторой:\n\n" +
                        aboutMethods.get(1) + "\n\nТретий\n\n" + aboutMethods.get(2));
                break;
            }

        }
    }
}