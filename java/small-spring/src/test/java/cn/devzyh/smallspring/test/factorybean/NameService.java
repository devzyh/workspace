package cn.devzyh.smallspring.test.factorybean;

public class NameService {

    private INameDao nameDao;

    private String area;

    void printFirstName() {
        System.out.println("IUserDao对象：" + nameDao + "ID为1的姓名是：" + nameDao.queryName(1));
    }

    void printArea() {
        System.out.println("对象：" + this + "区域：" + area);
    }
}
