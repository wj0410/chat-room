package io.github.wj0410.chatroom.system.query;

import io.github.wj0410.cloudbox.easycrud.mp.query.PageQuery;
import io.github.wj0410.cloudbox.easycrud.mp.annotation.Keyword;
import io.github.wj0410.cloudbox.easycrud.mp.annotation.Query;
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
