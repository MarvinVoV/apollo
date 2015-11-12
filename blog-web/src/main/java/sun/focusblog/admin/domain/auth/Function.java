package sun.focusblog.admin.domain.auth;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by root on 2015/11/7.
 *
 * Function entity represent resources which were authorized to access.
 */
public class Function {

    private int functionId;

    private String functionName;

    private List<Function> functions = new LinkedList<>();
    /**
     * this field help to query sub function as a query condition
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
