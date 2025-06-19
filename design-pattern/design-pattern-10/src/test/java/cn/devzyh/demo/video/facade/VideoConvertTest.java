package cn.devzyh.demo.video.facade;

import org.junit.Test;

public class VideoConvertTest {

    @Test
    public void convert() {
        new VideoConvert().convert("test", "mpeg4");
    }

}