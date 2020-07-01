package the.flash.protocol.request;

import lombok.Data;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * 加入群请求
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    /**
     * 群组id
     */
    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
