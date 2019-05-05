package edu.kapset.studentorder.domain.register;

/*
класс для хранения ответа из ГРН о регистрации гражданина
 */
public class CityRegisterResponse {
    private boolean existing;
    private Boolean temporal;
    /*
    т.к. переменная temporal может вообще отсутствовать в ответе (если человек не зарегистрирован),
    то её тип лучше определить классом-обёрткой, чтобы была возможность присовить значение null
     */

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public Boolean getTemporal() {
        return temporal;
    }

    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "existing=" + existing +
                ", temporal=" + temporal +
                '}';
    }
}
