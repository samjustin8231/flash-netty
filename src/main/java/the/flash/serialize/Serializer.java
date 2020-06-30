package the.flash.serialize;

import the.flash.serialize.impl.JSONSerializer;

/**
 * 序列化接口
 */
public interface Serializer {

    /**
     * 默认序列化方法：json序列化
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
