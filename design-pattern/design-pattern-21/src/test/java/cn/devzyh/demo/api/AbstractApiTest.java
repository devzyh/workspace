package cn.devzyh.demo.api;

import cn.devzyh.demo.api.api.AlibabaApi;
import cn.devzyh.demo.api.api.TencentApi;
import org.junit.Test;

public class AbstractApiTest {

    @Test
    public void getApiData() {
        AbstractApi ali = new AlibabaApi();
        System.out.println(ali.getApiData("user","pwd","test"));

        AbstractApi tx = new TencentApi();
        System.out.println(tx.getApiData("user","pwd","test"));
    }
}