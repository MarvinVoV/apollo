package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.focusblog.admin.dao.IFunctionDao;
import sun.focusblog.admin.domain.auth.Function;
import sun.focusblog.admin.services.FunctionService;

/**
 * Created by root on 2015/11/19.
 */
@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private IFunctionDao functionDao;

    @Override
    public Function query(int id) {
        return functionDao.queryFunctionById(id);
    }
}
