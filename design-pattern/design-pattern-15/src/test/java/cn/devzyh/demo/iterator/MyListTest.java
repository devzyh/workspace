package cn.devzyh.demo.iterator;

import org.junit.Test;

public class MyListTest {

    @Test
    public void run() {
        MyList<String> ml = new MyList<>();
        ml.add("1");
        ml.add("3");
        ml.add("5");
        ml.add("7");
        ml.add("9");

        Iterator<String> itr = ml.iter();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        System.out.println("=======================");

        itr = ml.iter();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}