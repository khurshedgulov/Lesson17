package company.my.lesson15;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MasterFragment extends Fragment {
    // Виджет для показа списка
    ListView listView;
    // Переменная для получения доступа к функциям работы с базой данных
    Database db;

    // Функция вызывается при показе фрагмента внутри (в контексте) Activity
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Инициализация переменной доступа к функциям работы с базой данных
        db = new Database(getContext());

       // Добавить файл макета master_fragment.xml к этому фрагменту
        View v = inflater.inflate(R.layout.master_fragment, container, false);

        // Получение экземпляра ListView размещенного в макете
        listView = v.findViewById(R.id.list);

        // Объявление и инициализация экземпляра адаптера класса SimpleListAdapter
        // адаптер служит для соединения данных с виджетом для показа
        // Функция getTitles() написанная в классе Database служит для получения заголовков
        // хранимой в базе данных информации
        SampleListAdapter adapter = new SampleListAdapter(getContext(), db.getTitles());
        // привязать адаптер к ListView
        listView.setAdapter(adapter);
        // Указать обработчик кликов по элементам списка
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Каждый раз при клике на какой-либо элемент вызывается функция onItemClick
            // и ей передаётся в качестве параметра корневой элемент макета, в данном случае LinearLayout
            // позиция выбранного элемента и id элемента
            // Через корневой элемент макета переданного через экземпляр класса View
            // можно получить ссылку к любому объекту в макете. Например, через их id.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Получить ссылку на TextView в файле макета по id
                TextView textView = view.findViewById(R.id.text);
                // Получисть и присвоить значение заголовка выбранного элемента переменной
                String title = textView.getText().toString();
                // Получить и присвоить значение ID из атрибута tag TextView переменной
                int index = (int)textView.getTag();

                // Объявлеине экземпляра переменной DetailFragment
                DetailFragment detail = new DetailFragment();
                // Переменная класса Bundle для передачи информации экземпляру класса DetailFragment
                Bundle args = new Bundle();
                // Добавить целочисленное значение с ключом id и индексом выбранного элемента из списка
                args.putInt("id", index);
                // Установить аргументы в экземпляр фрагмента
                detail.setArguments(args);

                // Получить ссылку на Activity в котором показывается данный фрагмент
                getActivity()
                        // Получить доступ менеджеру фрагментов
                        .getSupportFragmentManager()
                        // Начать транзакцию фрагментов
                        .beginTransaction()
                        // Заменить этот фрагмент со списком заголовков на фрагмент с текстом описания
                        .replace(R.id.fragmentContainer, detail)
                        // Добавить в стэк инициализированных фрагментов, чтобы при нажатии на кнопку назад
                        // пользователю выводился этот фрагмент, если эту строку не писать при нажатии
                        // на кнопку назад Activity закрывается сразу
                        .addToBackStack(null)
                        // Установить анимацию для показа DetailFragment
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        // Завершить транзакцию фрагмента
                        .commit();
            }
        });
        // Вернуть View, в данном случае LinearLayout из файла master_fragment.xml
        return v;
    }
}
