package cn.devzyh.demo.command.command;

import cn.devzyh.demo.command.AbstractCommand;

/**
 * 复制命令实现
 */
public class CopyCommand extends AbstractCommand {

    @Override
    public boolean exec() {
        print("Copy");
        return true;
    }
}
