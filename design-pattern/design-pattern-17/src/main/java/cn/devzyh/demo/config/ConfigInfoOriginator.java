package cn.devzyh.demo.config;

/**
 * 配置信息备忘记录者
 * 备忘信息的操作类
 */
public class ConfigInfoOriginator {

    private ConfigInfo configInfo;

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    /**
     * 新增备忘快照
     *
     * @return
     */
    public ConfigInfoMemento saveMemento() {
        return new ConfigInfoMemento(this.configInfo);
    }

    /**
     * 快照恢复到当前对象
     *
     * @param memento
     */
    public void getMemento(ConfigInfoMemento memento) {
        this.configInfo = memento.getMemento();
    }
}
