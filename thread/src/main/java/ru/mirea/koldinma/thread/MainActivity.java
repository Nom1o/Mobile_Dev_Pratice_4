package ru.mirea.koldinma.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.koldinma.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
       // setContentView(R.layout.activity_main);

        TextView infoTextView = binding.textView;
        Thread mainThread = Thread.currentThread();

        TextView days = binding.dayTextEdit;
        TextView pars = binding.parTextEdit;


        infoTextView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-01-20, НОМЕР ПО СПИСКУ: 12, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Аркейн");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),
                "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток No %d студентом группы " +
                                "No %s номер по списку No %d ", numberThread, "БСБО-01-20", -1));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();

                runOnUiThread(new Runnable() {
                    public void run() {

                            int lessons= (Integer.parseInt( pars.getText().toString()))/
                                    Integer.parseInt(days.getText().toString());
                            binding.lessonsTextView.setText("Среднее количество пар = " +
                                    lessons);


                    }
                });
            }
        });

        setContentView(binding.getRoot());
    }

}


