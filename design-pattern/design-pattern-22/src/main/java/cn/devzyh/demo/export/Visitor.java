package cn.devzyh.demo.export;

import cn.devzyh.demo.export.shape.Circle;
import cn.devzyh.demo.export.shape.Dot;
import cn.devzyh.demo.export.shape.Square;

/**
 * 访问者方法
 * 在这里实现每个类型的具体访问方式
 */
public class Visitor {

    /**
     * 访问点
     *
     * @param dot
     * @return
     */
    public String visitDot(Dot dot) {
        dot.func();
        return "访问了一个点";
    }

    /**
     * 访问圆
     *
     * @param circle
     * @return
     */
    public String visitCircle(Circle circle) {
        circle.func();
        return "访问了一个圆";
    }

    /**
     * 访问正方形
     *
     * @param square
     * @return
     */
    public String visitSquare(Square square) {
        square.func();
        return "访问了一个正方形";
    }

}
