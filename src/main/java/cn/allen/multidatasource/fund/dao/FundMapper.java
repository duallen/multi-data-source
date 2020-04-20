package cn.allen.multidatasource.fund.dao;

import cn.allen.multidatasource.fund.entity.Fund;
import org.apache.ibatis.annotations.Param;

/**
 * @author allen
 * @date 2020-04-20
 */
public interface FundMapper {

    void add(Fund fund);

    void delete(@Param("fundCode") String fundCode);

    void update(Fund fund);

    Fund get(@Param("fundCode") String fundCode);
}
