package sun.focusblog.admin.dao;

import sun.focusblog.admin.domain.auth.Function;

/**
 * Created by root on 2015/11/19.
 */
public interface IFunctionDao {
    String NAMESPACE = "sun.focusblog.admin.domain.auth.Function";

    Function queryFunctionById(int id);
}
