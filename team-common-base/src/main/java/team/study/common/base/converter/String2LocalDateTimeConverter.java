package team.study.common.base.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static team.study.common.base.utils.DateUtil.*;


/**
 * 解决入参为 Date类型
 *
 * @author zuihou
 * @date 2019-04-30
 */
public class String2LocalDateTimeConverter extends BaseDateConverter<LocalDateTime> implements Converter<String, LocalDateTime> {

    protected static final Map<String, String> FORMAT = new LinkedHashMap(5);

    static {
        FORMAT.put(DEFAULT_DATE_TIME_FORMAT, DEFAULT_DATE_TIME_FORMAT_MATCHES);
        FORMAT.put(SLASH_DATE_TIME_FORMAT, SLASH_DATE_TIME_FORMAT_MATCHES);
        FORMAT.put(DEFAULT_DATE_TIME_FORMAT_EN, DEFAULT_DATE_TIME_FORMAT_EN_MATCHES);
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalDateTime convert(String source) {
        return super.convert(source, (key) -> LocalDateTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
