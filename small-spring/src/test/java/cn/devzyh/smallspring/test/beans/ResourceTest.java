package cn.devzyh.smallspring.test.beans;

import cn.devzyh.smallspring.core.io.DefaultResourceLoader;
import cn.devzyh.smallspring.core.io.Resource;
import cn.devzyh.smallspring.core.io.ResourceLoader;
import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ResourceTest {

    /**
     * 测试资源加载器
     */
    @Test
    public void testClassPathResourceLoader() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:test.conf");
        System.out.println(IoUtil.readUtf8(resource.getInputStream()));
    }

    /**
     * 测试文件资源加载器
     */
    @Test
    public void testFileSystemResourceLoader() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("D:\\SourceCode\\demo\\small-spring\\src\\test\\resources\\test.conf");
        System.out.println(IoUtil.readUtf8(resource.getInputStream()));
    }

    /**
     * 测试远程资源加载器
     */
    @Test
    public void testUrlResourceLoader() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("https://devzyh.cn");
        System.out.println(IoUtil.readUtf8(resource.getInputStream()));
    }
}
