package cn.devzyh.demo.config;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;

public class ConfigInfoMementoAdminTest {

    @Test
    public void run() {
        // 备忘信息管理者
        ConfigInfoMementoAdmin admin = new ConfigInfoMementoAdmin();

        // 配置文件记录者
        ConfigInfoOriginator originator = new ConfigInfoOriginator();
        originator.setConfigInfo(new ConfigInfo("tb", "tb", "taobao.com"));
        admin.append(originator.saveMemento());

        originator.setConfigInfo(new ConfigInfo("tm", "tm", "tmall.com"));
        admin.append(originator.saveMemento());

        originator.setConfigInfo(new ConfigInfo("jd", "jd", "jd.com"));
        admin.append(originator.saveMemento());

        // 回退
        System.out.println("回滚上一版");
        originator.getMemento(admin.undo());
        System.out.println(JSON.toJSONString(originator.getConfigInfo()));

        // 撤销
        System.out.println("撤销操作");
        originator.getMemento(admin.redo());
        System.out.println(JSON.toJSONString(originator.getConfigInfo()));
    }

}
