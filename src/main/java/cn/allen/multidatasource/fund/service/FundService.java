package cn.allen.multidatasource.fund.service;

import cn.allen.multidatasource.fund.entity.Fund;

/**
 * @author allen
 * @date 2020-04-20
 */
public interface FundService {
    void add(Fund fund);

    void delete(String fundCode);

    void update(Fund fund);

    Fund get(String fundCode);
}
