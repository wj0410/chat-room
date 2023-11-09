package io.github.wj0410.chatroom.system.query;

import io.github.wj0410.easycrudmp.annotation.Keyword;
import io.github.wj0410.easycrudmp.annotation.Query;
import io.github.wj0410.easycrudmp.query.PageQuery;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wangjie
 */
@Data
public class UserQuery extends PageQuery {
    @Query(keyword=Keyword.like)
    private String name;
    @Query(keyword = Keyword.order_asc)
    private LocalDateTime createTime;
}
