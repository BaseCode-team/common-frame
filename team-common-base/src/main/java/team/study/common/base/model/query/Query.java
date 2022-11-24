package team.study.common.base.model.query;

import team.study.common.base.model.dto.Command;

import java.io.Serial;

/**
 * 查询基类
 *
 * @author JiaHao
 * @date 2022-11-22 14:22
 */
public abstract class Query extends Command {
    @Serial
    private static final long serialVersionUID = 1L;

    public Query() {
    }
}
