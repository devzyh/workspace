package cn.devzyh.demo.growing;

import java.io.*;

/**
 * try-with-resources 是一个定义了一个或多个资源的 try 声明，这个资源是指程序处理完它之后需要关闭它的对象。
 * try-with-resources 确保每一个资源在处理完成后都会被关闭。
 * 可以使用try-with-resources的资源有： 任何实现了 java.lang.AutoCloseable 接口 java.io.Closeable 接口的对象。
 */
public class TryWithResources {

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DEVZYH\\Desktop\\2022Q3\\oaid.txt"))
        ) {
            System.out.println(br.readLine());

            throw new EOFException();
        } catch (FileNotFoundException | EOFException e) { // 这里或的异常类型必须是同级的
            //捕获多个异常，e就是final类型的
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
