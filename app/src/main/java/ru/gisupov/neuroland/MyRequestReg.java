package ru.gisupov.neuroland;


/**
 * Наследник класса MyRequest.
 * Нужен для создания запроса на регистрацию
 */
public class MyRequestReg extends MyRequest {
    private final String name;
    private final String pass1;
    private final String pass2;

    public MyRequestReg(String doing, String[] data) {
        super(doing, new String[] {data[0], data[1]});
        this.name = data[0];
        this.pass1 = data[1];
        this.pass2 = data[2];
    }

    /**
     * Проверяет данные на корректность
     * @return 1, если всё введено правильно, иначе текст ошибки
     */
    public int checkNamePass() {
        short loginSize = (short) name.length();
        short passSize = (short)  pass1.length();
        if (!this.pass1.equals(this.pass2))
            return R.string.password_not_same;
        else if (loginSize == 0 && passSize == 0)
            return R.string.emptyLogReg;
        else if (loginSize == 0)
            return R.string.emptyLog;
        else if (passSize == 0)
            return R.string.emptyReg;
        else {
            if (passSize < 8)
                return R.string.shortReg;
            else if (loginSize < 4)
                return R.string.shortLog;
        }
        return 1;
    }
}
