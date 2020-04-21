package cn.allen.multidatasource.mdscomponent.exception;

import cn.allen.multidatasource.mdscomponent.enumuration.MultiDsExceptionEnum;

/**
 * @author allen
 * @date 2020-04-21
 */
public class FormatTableSuffixException extends MultiDsException {

    public FormatTableSuffixException(){
        super();
        setExceptionCode(MultiDsExceptionEnum.FORMAT_TABLE_SUFFIX_ERROR.getCode());
        setMsg(MultiDsExceptionEnum.FORMAT_TABLE_SUFFIX_ERROR.getMsg());
    }
}
