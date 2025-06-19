package cn.devzyh.demo.config;

/**
 * 配置信息备忘录对象
 * 封装需要备忘的对象
 */
public class ConfigInfoMemento {

    // 备忘对象
    private ConfigInfo configInfo;

    public ConfigInfoMemento(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public ConfigInfo getMemento() {
        return configInfo;
    }

    public void setMemento(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }
}
