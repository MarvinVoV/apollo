package sun.yamorn.blog.admin.domain.auth;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 */
public class BaseFunction implements IFunction{
    protected int functionId;

    protected String functionName;

    protected List<IFunction> functions = new LinkedList<>();

    @Override
    public int getFunctionId() {
        return this.functionId;
    }

    @Override
    public String getFunctionName() {
        return this.functionName;
    }


    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public List<IFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<IFunction> functions) {
        this.functions = functions;
    }
}
