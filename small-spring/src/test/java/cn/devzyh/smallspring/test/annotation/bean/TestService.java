package cn.devzyh.smallspring.test.annotation.bean;

import cn.devzyh.smallspring.context.annotation.Autowired;
import cn.devzyh.smallspring.context.annotation.Component;
import cn.devzyh.smallspring.context.annotation.Value;

@Component("testService")
public class TestService implements ITestService {

    private String word;

    @Value("${key}")
    private String key;

    @Autowired
    TestMapper testMapper;

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public void hello() {
        System.out.println("Hello " + word);
    }

    @Override
    public void printName() {
        System.out.println(testMapper.queryName(key));
    }
}
