package com.eezd.common.utils.uuid;

/**
 * ID生成器工具类
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     * exam: 7840f4d0-94a1-4f05-bcda-78c2e883e036
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     * exam: 2ff045c2286f4a73b31d3bce537b022d
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     * exam: 7840f4d0-94a1-4f05-bcda-78c2e883e036
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     * exam: 2ff045c2286f4a73b31d3bce537b022d
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }
}
