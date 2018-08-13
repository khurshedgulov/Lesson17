package company.my.lesson15;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailFragment extends Fragment {
    // В файле макета разместили два TextView один для заголовка,
    // второй для показа содежимого, для них соответственно необходимо
    // объявить перменные для управления ими через код
    TextView title;
    TextView content;
    // Пременная для доступа к функциям работы с базой данных
    Database db;

    // Функция вызываемая при показе фрагмента внутри Activity
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Привязать файл макета detail_fragment.xml к этому фрагменту
        View v = inflater.inflate(R.layout.detail_fragment, container, false);

        // Инициализация переменной класса Database для работы с базой данных
        db = new Database(getContext());

        // Получить ссылку на TextView для заголовка и контента
        title = v.findViewById(R.id.title);
        content = v.findViewById(R.id.content);

        // Получить переданные аргументы по соответствующим ключам
        int id = getArguments().getInt("id");

        // Объявить экземпляр класса DBItem и присвоить значение полученное
        // посредством запроса по уникальному id из базы данных
        DBItem item = db.getItemById(id);
        // Присвоеить значение заголовка виджету TextView
        title.setText(item.title);

        // Присвоить текст к TextView контента
        // При использовании Html.fromHtml(String) можно текст TextView
        // форматировать по необходимости Html тегами, например для вывода жирного
        // текста в строке надо написать <b>Жирный текст</b>, также можно использовать
        // теги u,i и другие используемые для форматирования текста в html
        // Например ещё тег <br/> устанавливает перевод строки
        content.setText(Html.fromHtml(item.content));

        // Вернуть View для показа внутри Activity
        return v;
    }
}
