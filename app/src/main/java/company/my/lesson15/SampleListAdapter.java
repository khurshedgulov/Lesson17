package company.my.lesson15;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Класс предоставляющий возможность определения поведения адаптера
// для правильной привязки информации к виджетам
public class SampleListAdapter extends BaseAdapter {
    // Объявление необходимых перенных получаемых через конструктор
    // Объект Context необходим для получения ссылки на Activity
    // в котором адаптер подключается к ListView
    Context context;
    // Массив для хранения элементов для привязки к ListView
    List<DBItem> items = new ArrayList<>();

    // Конструктор класса адаптера, принимает параметры контекста и элементов переданных с Activity
    public SampleListAdapter(Context context, List<DBItem> items)
    {
        // Присвоить полученные параметры локальным переменным класса
        this.context = context;
        this.items = items;
    }

    // Функция для возвращения количества элементов
    @Override
    public int getCount() {
        return items.size();
    }

    // Получить элемент для показа в необходимой позиции
    @Override
    public DBItem getItem(int position) {
        return items.get(position);
    }

    // Получить id элемента, обычно возвращается запрашиваемое значение переданное по параметру,
    // потому что в массиве расположение элементов идёт по порядку и так и показывается в ListView на данный момент.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Получить корневой View, в котором и распологаются виджеты
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Если View ещё не создавался создать
        // иначе использовать уже созданный ранее view
        if(view == null)
        {
            // Получить ссылку к экземпляру LayoutInflater для получения xml файла макета
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Запросить файл макета и присвоить переменной view класса View
            // теперь view это LinearLayout объявленный в файле adapter_item.xml
            // В файле adapter_item.xml определяется макет для одной строки списка
            // потому что структура всех строк списка одинаковая
            view = inflater.inflate(R.layout.adapter_item, parent, false);
        }
        // Найти ссылку на TextView внутри LinearLayout файла макета
        TextView text = view.findViewById(R.id.text);
        // Установить значение для TextView равной значению элемента массива
        // соответствующей позиции данной строки
        text.setText(items.get(position).title);
        // установить в качестве тега TextView значение позиции элемента
        // тег может хранить в себе объект и служит для дальнейшего определения этого объекта по тегу
        // или хранения и обработки информации, которую нет нужды показывать в интерфейсе пользователю
        text.setTag(items.get(position).id);
        // Вернуть объект View - корневой LinearLayout из файла adapter_item.xml
        return view;
    }
}
