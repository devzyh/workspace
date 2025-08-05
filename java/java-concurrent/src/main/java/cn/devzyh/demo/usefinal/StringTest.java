package cn.devzyh.demo.usefinal;

/**
 * 字符串测试
 *
 * @author devzyh
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = new String("123456");
        String s2 = s1;
        s1 = "456780"; // 改变s1的引用值
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2); // 此处输出123456
        // 字符串内部维护一个字符串常量池，内容一样的字符串将会指向同一个字符串对象
        // 字符串对象一旦创建无法修改，更改值也会产生新的对象
    }

}
