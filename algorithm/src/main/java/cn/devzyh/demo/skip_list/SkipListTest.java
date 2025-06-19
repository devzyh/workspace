package cn.devzyh.demo.skip_list;

/**
 * 跳表测试类
 */
public class SkipListTest {

    public static void main(String[] args) {
        String[] methods = new String[]{"add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "add", "search", "search", "search", "add", "search", "erase", "add", "add", "search", "erase", "search", "add", "erase", "search", "add", "search", "erase", "search", "search", "search", "search", "erase", "add", "search", "add", "add", "search", "erase", "add", "erase", "erase", "search", "erase", "add", "search", "erase", "search", "erase", "erase", "add", "search", "add", "search", "add", "erase", "search", "search", "add", "add", "add", "add", "add", "erase", "search", "add", "erase", "erase", "erase", "erase", "add", "search", "erase", "search", "erase", "add", "add", "add", "search", "search", "search", "erase", "search", "search", "search", "search", "search", "search", "add", "erase", "add", "add", "search", "search", "search", "search", "add", "search", "add", "add", "erase", "search", "search", "add", "add", "add", "erase", "add", "erase", "search", "erase", "erase", "add", "erase", "search", "search", "erase", "add", "erase", "search", "erase", "add", "search", "add", "search", "erase", "search", "erase", "add", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search", "search"};
        int[] inputs = new int[]{9, 16, 33, 8, 13, 2, 11, 14, 15, 4, 17, 18, 15, 24, 29, 30, 3, 10, 11, 12, 5, 6, 9, 30, 9, 24, 13, 30, 7, 16, 25, 26, 31, 24, 9, 24, 3, 10, 3, 8, 11, 2, 19, 20, 21, 10, 1, 32, 33, 12, 23, 22, 1, 22, 7, 10, 27, 0, 25, 32, 25, 2, 25, 18, 15, 2, 1, 20, 13, 10, 17, 4, 17, 2, 23, 0, 29, 0, 13, 20, 23, 10, 25, 16, 31, 20, 19, 6, 1, 12, 21, 16, 19, 24, 7, 22, 11, 22, 15, 2, 11, 4, 15, 30, 13, 24, 15, 4, 13, 6, 5, 24, 1, 26, 33, 14, 23, 18, 9, 12, 23, 0, 3, 4, 17, 12, 1, 18, 5, 18, 9, 32, 27, 0, 29, 16, 15, 2, 5, 12, 3, 32, 27, 32, 5, 8, 15, 4, 29, 24, 11, 8, 33};
        SkipList sl = new SkipList();
        for (int i = 0; i < inputs.length; i++) {
            switch (methods[i]) {
                case "add":
                    sl.add(inputs[i]);
                    break;
                case "erase":
                    sl.erase(inputs[i]);
                    break;
                case "search":
                    sl.search(inputs[i]);
                    break;
            }
//            if (i == 131) {
//                break;
//            }
        }
        System.out.println(sl);


    }

}
