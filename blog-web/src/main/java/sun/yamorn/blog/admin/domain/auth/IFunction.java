package sun.yamorn.blog.admin.domain.auth;

import java.util.List;

/**
 * Created by root on 2015/11/7.
 */
public interface IFunction {

    int getFunctionId();

    String getFunctionName();

    List<IFunction> getFunctions();


}
