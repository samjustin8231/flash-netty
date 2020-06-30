package the.flash.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import the.flash.attribute.Attributes;

/**
 * 登录工具类
 */
public class LoginUtil {

    /**
     * 标记为登录成功
     *
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断登录状态是否为登录成功
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
