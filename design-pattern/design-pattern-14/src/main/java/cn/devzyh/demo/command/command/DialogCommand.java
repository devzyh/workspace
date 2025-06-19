package cn.devzyh.demo.command.command;

import cn.devzyh.demo.command.AbstractCommand;

/**
 * 弹窗命令实现
 */
public class DialogCommand extends AbstractCommand {

    @Override
    public boolean exec() {
        print("Copy");
        return true;
    }
}
