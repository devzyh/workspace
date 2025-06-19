package cn.devzyh.demo.command;

import cn.devzyh.demo.command.command.CopyCommand;
import cn.devzyh.demo.command.command.DialogCommand;
import cn.devzyh.demo.command.command.PasteCommand;
import org.junit.Test;

public class CommandHistoryTest {

    @Test
    public void run() {
        CommandHistory ch = new CommandHistory();
        ch.exec(new CopyCommand());
        ch.exec(new PasteCommand());
        ch.exec(new DialogCommand());
        ch.exec(new DialogCommand());
        ch.exec(new PasteCommand());
        ch.undo();
        ch.undo();
        ch.undo();
    }
}