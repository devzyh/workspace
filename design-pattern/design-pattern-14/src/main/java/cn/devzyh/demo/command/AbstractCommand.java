package cn.devzyh.demo.command;

/**
 * 命令抽象类
 */
public abstract class AbstractCommand {

    /**
     * 提供的公共方法
     *
     * @param cmd
     */
    protected void print(String cmd) {
        System.out.println("执行了命令：" + cmd);
    }

    /**
     * 具体命令需要实现的操作
     *
     * @return
     */
    public abstract boolean exec();

}
