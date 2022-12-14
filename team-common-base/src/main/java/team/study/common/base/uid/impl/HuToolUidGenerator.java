package team.study.common.base.uid.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import team.study.common.base.exception.UidGenerateException;
import team.study.common.base.uid.UidGenerator;

/**
 * 基于Hu tool 工具类实现的雪花id生成器
 *
 * @author JiaHao
 * @date 2022-12-13 14:50
 */
public class HuToolUidGenerator implements UidGenerator {
    private final Snowflake snowflake;

    public HuToolUidGenerator(long workerId, long datacenterId) {
        this.snowflake = IdUtil.getSnowflake(workerId, datacenterId);
    }

    @Override
    public long getUid() throws UidGenerateException {
        return snowflake.nextId();
    }

    @Override
    public String parseUid(long uid) {
        long workerId = snowflake.getWorkerId(uid);
        long dataCenterId = snowflake.getDataCenterId(uid);
        long timestamp = snowflake.getGenerateDateTime(uid);

        return String.format("{\"UID\":\"%d\",\"timestamp\":\"%d\",\"workerId\":\"%d\",\"dataCenterId\":\"%d\"}",
                uid, timestamp, workerId, dataCenterId);
    }
}