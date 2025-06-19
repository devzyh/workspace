package cn.devzyh.demo.command.command;

import cn.devzyh.demo.command.AbstractCommand;

/**
 * 粘贴命令实现
 */
public class PasteCommand extends AbstractCommand {

    @Override
    public boolean exec() {
        print("Paste");
        return true;
    }
}
