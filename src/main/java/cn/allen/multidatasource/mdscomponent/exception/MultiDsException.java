package cn.allen.multidatasource.mdscomponent.exception;

import lombok.Data;

/**
 * @author allen
 * @date 2020-04-21
 */
@Data
public class MultiDsException extends RuntimeException {
    private Integer exceptionCode;
    private String msg;
}
