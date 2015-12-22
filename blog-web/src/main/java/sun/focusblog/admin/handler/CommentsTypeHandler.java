package sun.focusblog.admin.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.framework.cache.util.JsonSerializeUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 2015/12/22.
 */
@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
public class CommentsTypeHandler extends BaseTypeHandler<List<Comment>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Comment> parameter, JdbcType jdbcType) throws SQLException {
        String json = JsonSerializeUtils.toJsonString(parameter);
        ps.setString(i, json);
    }

    @Override
    public List<Comment> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (json != null) {
            return JsonSerializeUtils.toList(json, Comment.class);
        } else {
            return null;
        }
    }

    @Override
    public List<Comment> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        if (json != null) {
            return JsonSerializeUtils.toList(json, Comment.class);
        } else {
            return null;
        }
    }

    @Override
    public List<Comment> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        if (json != null) {
            return JsonSerializeUtils.toList(json, Comment.class);
        } else {
            return null;
        }
    }
}
