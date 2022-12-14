package team.study.common.base.uid;

import team.study.common.base.exception.UidGenerateException;

public interface UidGenerator {
    /**
     * Get a unique ID
     *
     * @return UID
     * @throws UidGenerateException
     */
    long getUid() throws UidGenerateException;

    /**
     * Parse the UID into elements which are used to generate the UID. <br>
     * Such as timestamp & workerId & sequence...
     *
     * @param uid
     * @return Parsed info
     */
    String parseUid(long uid);
}
