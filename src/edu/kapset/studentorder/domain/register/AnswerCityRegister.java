package edu.kapset.studentorder.domain.register;

import java.util.ArrayList;
import java.util.List;

// класс для создания объекта, который содержит в себе результат проверки
// класс содержит ответ по группе людей - семье
public class AnswerCityRegister {
    private List<AnswerCityRegisterItem> items;

    public void addItem (AnswerCityRegisterItem item) {
        if (items == null) {
            items = new ArrayList<>(10);
        }
        items.add(item);
    }

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }
}
