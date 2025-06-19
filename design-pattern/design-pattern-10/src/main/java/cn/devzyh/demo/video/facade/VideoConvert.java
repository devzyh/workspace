package cn.devzyh.demo.video.facade;

import cn.devzyh.demo.video.VideoFile;

/**
 * 音频转换器外观
 */
public class VideoConvert {

    public VideoFile convert(String name, String format) {
        System.out.println("执行调用音频模块复杂的初始化和调用逻辑完成音频转换操作");
        return new VideoFile(name);
    }
}
