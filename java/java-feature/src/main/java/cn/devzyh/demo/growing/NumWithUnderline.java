package cn.devzyh.demo.growing;

/**
 * JDK1.7可以在数值类型的变量里添加下滑线，但是有几个地方是不能添加的
 * <p>
 * 数字的开头和结尾
 * 小数点前后
 * F或者L前
 */
public class NumWithUnderline {

    public static void main(String[] args) {
        // 数字下划线测试
        int num = 123_343_22;
        System.out.println(num);
    }
}
