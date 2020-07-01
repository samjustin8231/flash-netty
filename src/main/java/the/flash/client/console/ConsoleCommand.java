package the.flash.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令接口，有多种控制台命令：创建group，登录，发消息，退群
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
