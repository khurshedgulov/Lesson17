package company.my.lesson15;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Проверить на существование присвоенного значения
        // переменной savedInstanceState
        // Если значение равно null значит Activity только что запустился
        // и можно добавить фрагмент, если не писать эту проверку, каждый раз при
        // восстановлении состояния Activity, например в случае поворота экрана
        // может возникнуть подобная ситуация, фрагмент будет добавляться в Activity
        if (savedInstanceState == null) {
            // Получить экземпляр менеджера фрагментов
            getSupportFragmentManager()
                    // Начать транзакцию фрагментов
                    .beginTransaction()
                    // Добавить в LinearLayout с id = fragmentContainer содержимое фрагмента
                    // MasterFragment
                    .add(R.id.fragmentContainer, new MasterFragment())
                    // Установить анимацию для показа фрагмента
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    // Завершить транзакцию и показать фрагмент
                    .commit();
        }
    }
}
