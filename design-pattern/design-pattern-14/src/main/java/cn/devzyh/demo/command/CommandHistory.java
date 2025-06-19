package cn.devzyh.demo.command;

import java.util.Stack;

/**
 * 命令历史操作
 */
public class CommandHistory {

    // 存储命令执行历史
    private Stack<AbstractCommand> history = new Stack<>();

    /**
     * 执行命令
     *
     * @param cmd
     */
    public void exec(AbstractCommand cmd) {
        System.out.println("执行：");
        cmd.exec();
        history.push(cmd);
    }

    /**
     * 撤销命令
     */
    public void undo() {
        System.out.println("撤销：");
        AbstractCommand cmd = history.pop();
        cmd.exec();
    }

}
