package cn.allen.multidatasource.mdscomponent.exception;

import cn.allen.multidatasource.mdscomponent.enumuration.MultiDsExceptionEnum;

/**
 * @author allen
 * @date 2020-04-21
 */
public class LoadRoutingStrategyUnMatchException extends MultiDsException {
    public LoadRoutingStrategyUnMatchException(){
        super();
        setExceptionCode(MultiDsExceptionEnum.LOADING_STRATEGY_UN_MATCH.getCode());
        setMsg(MultiDsExceptionEnum.LOADING_STRATEGY_UN_MATCH.getMsg());
    }
}
