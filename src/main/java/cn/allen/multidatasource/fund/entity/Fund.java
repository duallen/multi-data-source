package cn.allen.multidatasource.fund.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author allen
 * @date 2020-04-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fund extends BaseDomain implements Serializable {
    private String fundCode;

    private String fundName;
}
