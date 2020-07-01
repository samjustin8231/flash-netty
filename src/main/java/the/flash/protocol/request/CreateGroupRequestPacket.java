package the.flash.protocol.request;

import lombok.Data;
import the.flash.protocol.Packet;

import java.util.List;

import static the.flash.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * 创建群组的请求
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    /**
     * 群组成员ids
     */
    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
