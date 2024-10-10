package com.example.lab2;

import android.os.Bundle;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private TextView infoTextView;
    private Button infoButton;
    private View.OnTouchListener gestureListener;
    private TextView gestureText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTextView = findViewById(R.id.infoTextView);
        gestureText = findViewById(R.id.gestureText);
        gestureDetector = new GestureDetector(this, this);
        infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String appInfo = "Приложение для распознавания сенсорных действий пользователя. Разработал Куликович И.С. ";
                Toast.makeText(MainActivity.this, appInfo, Toast.LENGTH_LONG).show();
            }
        });


        View rootLayout = getWindow().getDecorView().getRootView();

        gestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };


        rootLayout.setOnTouchListener(gestureListener);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gestureText.setText(""); // Очищаем поле gestureText при одиночном касании

        Toast.makeText(this, "Show press detected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gestureText.setText(""); // Очищаем поле gestureText при одиночном касании

        Toast.makeText(this, "Single tap up detected", Toast.LENGTH_SHORT).show();
        return true; // Возвращаем true, чтобы показать, что событие было обработано
    }

    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        gestureText.setText("Gesture: onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gestureText.setText(""); // Очищаем поле gestureText при одиночном касании

        Toast.makeText(this, "Long touch", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gestureText.setText(""); // Очищаем поле gestureText при одиночном касании
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();

        if (Math.abs(distanceX) > Math.abs(distanceY)) {
            if (Math.abs(distanceX) > 100 && Math.abs(velocityX) > 100) {
                if (distanceX > 0) {
                    Toast.makeText(this, "Swipe right", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Swipe left", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        } else {
            if (Math.abs(distanceY) > 100 && Math.abs(velocityY) > 100) {
                if (distanceY > 0) {
                    Toast.makeText(this, "Swipe down", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Swipe up", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        }

        return false;
    }
}