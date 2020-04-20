package cn.allen.multidatasource.fund.service.impl;

import cn.allen.multidatasource.fund.dao.FundMapper;
import cn.allen.multidatasource.fund.entity.Fund;
import cn.allen.multidatasource.fund.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author allen
 * @date 2020-04-20
 */
@Service
@Slf4j
public class FundServiceImpl implements FundService {

    @Autowired
    private FundMapper fundMapper;

    @Override
    public void add(Fund fund) {
        fundMapper.add(fund);
    }

    @Override
    public void delete(String fundCode) {
        fundMapper.delete(fundCode);
    }

    @Override
    public void update(Fund fund) {
        fundMapper.update(fund);
    }

    @Override
    public Fund get(String fundCode) {
        return fundMapper.get(fundCode);
    }
}
