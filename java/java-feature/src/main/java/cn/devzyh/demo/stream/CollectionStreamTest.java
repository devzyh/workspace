package cn.devzyh.demo.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合之流的操作
 */
public class CollectionStreamTest {

    public static void main(String[] args) {
        collectorOperation();
    }

    /**
     * 获取流对象
     */
    static void getStream() {
        List<Person> persons = new LinkedList<>();

        // 集合转为流对象
        Stream<Person> stream = persons.stream();

        // 通过Arrays类提供的静态函数stream()获取数组的流对象：
        String[] hobbies = {"唱歌", "跑步", "画画",};
        Stream<String> stream1 = Arrays.stream(hobbies);

        // 直接将几个值变成流对象
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);

        // 文件变为流对象
        try {
            Stream<String> stream3 = Files.lines(Paths.get("C:\\Users\\DEVZYH\\Desktop\\2022Q3\\oaid.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建无限流
        Stream.iterate(0, (n) -> n * 10)
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * 流中间操作
     * 中间操作仍然会返回一个流对象，因此多个中间操作可以串连起来形成一个流水线。
     */
    static void middleOperation() {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(1, "张三", Collections.singletonList("唱歌")));
        persons.add(new Person(2, "李四", Collections.singletonList("跳舞")));
        persons.add(new Person(3, "王五", Collections.singletonList("跑步")));
        persons.add(new Person(4, "赵六", Collections.singletonList("爬山")));
        Person p = new Person(5, "钱七", Collections.singletonList("画画"));
        persons.add(p);
        persons.add(p);
        persons.add(p);

        //  筛选 filter
        Stream<Person> stream = persons.stream();
        stream.filter((Person person) -> {
            return person.id > 3;
        }).forEach(System.out::println);

        // 去重distinct
        List<Person> res = persons.stream().distinct().collect(Collectors.toList());
        for (Person re : res) {
            System.out.println(re);
        }

        // 截取流的前N个元素
        persons.stream().limit(3).forEach(System.out::println);

        // 跳过流的前n个元素
        persons.stream().skip(3).forEach(System.out::println);

        // 对流中的每个元素执行一个函数，使得元素转换成另一种类型输出。
        // 流会将每一个元素输送给map函数，并执行map中的Lambda表达式，最后将执行结果存入一个新的流中。
        persons.stream().map((Person person) -> {
            person.hobbies = Collections.emptyList();
            return person;
        }).forEach(System.out::println);

        // 合并多个流
        List<String> lines = new ArrayList<>();
        lines.add("Hello World");
        lines.add("The Hello is blue");
        lines.add("Welcome to World");

        // 先通过空格分割，把它变成一个个小流
        lines.stream().map(line -> line.split(" "))
                .flatMap(Arrays::stream) // 使用flatMap将小流合并成一个流
                .distinct() // 对合并后的流去重
                .forEach(System.out::println); // 展示吧

        // anyMatch用于判断流中是否存在至少一个元素满足指定的条件
        System.out.println(persons.stream().anyMatch(p1 -> p1.id > 4));

        // allMatch用于判断流中的所有元素是否都满足指定条件
        System.out.println(persons.stream().allMatch(p1 -> p1.id > 4));

        // noneMatch与allMatch恰恰相反，它用于判断流中的所有元素是否都不满足指定条件
        System.out.println(persons.stream().noneMatch(p1 -> p1.id == 0));

        // findAny能够从流中随便选一个元素出来，它返回一个Optional类型的元素
        Optional<Person> any = persons.stream().findAny();
        System.out.println(any.get());

        // 获取第一个元素findFirst
        Optional<Person> first = persons.stream().findFirst();
        System.out.println(first.get());

        // 归约是将集合中的所有元素经过指定运算，折叠成一个元素输出，如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出。
        /**
         * 一般性归约
         * 若你需要自定义一个归约操作，那么需要使用 Collectors.reducing 函数，该函数接收三个参数：
         *
         * 第一个参数为归约的初始值
         * 第二个参数为归约操作进行的字段
         * 第三个参数为归约操作的过程
         */
        //元素求和：自定义Lambda表达式实现求和
        int[] nums = {1, 2, 4, 4, 2, 2};
        int result = Arrays.stream(nums).reduce(0, (a, b) -> a + b); // 0 结果表示初始值
        result = Arrays.stream(nums).reduce(0, Integer::sum); // 利用现有方法简写
        System.out.println(result);

        // 采用reduce进行数值操作会涉及到基本数值类型和引用数值类型之间的装箱、拆箱操作，因此效率较低。 当流操作为纯数值操作时，使用数值流能获得较高的效率。
        // 使用数值流找出最大id
        OptionalInt max = persons.stream().mapToInt(Person::getId).max();
        System.out.println(max.getAsInt());

    }

    /**
     * 终端操作
     * 终端操作将返回一个执行结果，这就是你想要的数据。
     */
    static void collectorOperation() {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(1, "张三", Collections.singletonList("唱歌")));
        persons.add(new Person(2, "李四", Collections.singletonList("跳舞")));
        persons.add(new Person(3, "王五", Collections.singletonList("跑步")));
        persons.add(new Person(4, "赵六", Collections.singletonList("爬山")));

        // Collectors类专门为汇总提供了一个工厂方法：Collectors.summingInt。 它可接受一 个把对象映射为求和所需int的函数，并返回一个收集器；
        // 该收集器在传递给普通的 collect 方法后即执行我们需要的汇总操作。
        Integer collect = persons.stream().collect(Collectors.summingInt(it -> it.id));
        System.out.println(collect);

        // 分组
        // 多级分组可以支持在完成一次分组后，分别对每个小组再进行分组。 使用具有两个参数的 groupingBy 重载方法即可实现多级分组。
        Map<Boolean, List<Person>> listMap = persons.stream().collect(Collectors.groupingBy(p -> p.id / 2 == 0));
        System.out.println(listMap);

        // 有一些收集器可以生成其他集合
        HashSet<Person> collect1 = persons.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect1);


        // 将一个顺序执行的流转变成一个并发的流只要调用 parallel() 方法
        Integer reduce = Stream.iterate(0, n -> n + 1).limit(5).parallel().reduce(0, Integer::sum);
        System.out.println(reduce);

        // 将一个并发流转成顺序的流只要调用 sequential() 方法
        Integer reduce1 = Stream.iterate(0, n -> n + 1).limit(5).parallel().sequential().reduce(0, Integer::sum);
        System.out.println(reduce1);

        // 这两个方法可以多次调用，只有最后一个调用决定这个流是顺序的还是并发的。
        // 并发流使用的默认线程数等于你机器的处理器核心数。
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12"); // 通过这个方法可以修改这个值，这是全局属性。
    }
}
