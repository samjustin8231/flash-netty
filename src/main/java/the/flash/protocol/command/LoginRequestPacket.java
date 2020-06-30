package the.flash.protocol.command;

import lombok.Data;

import static the.flash.protocol.command.Command.LOGIN_REQUEST;

/**
 * 登录指令
 */
@Data
public class LoginRequestPacket extends Packet {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
