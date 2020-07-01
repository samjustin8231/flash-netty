package the.flash.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.MESSAGE_REQUEST;

/**
 * 聊天请求
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    /**
     * 目标用户的id
     */
    private String toUserId;

    /**
     * 聊天内容
     */
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
