package cn.allen.multidatasource.fund.controller;

import cn.allen.multidatasource.fund.entity.Fund;
import cn.allen.multidatasource.fund.service.FundService;
import cn.allen.multidatasource.mdscomponent.annotation.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author allen
 * @date 2020-04-20
 */
@RestController
@RequestMapping("/api/v1/funds")
public class FundController {
    @Autowired
    private FundService fundService;

    @PostMapping
    @Router
    public String add(@RequestBody Fund fund){
        fundService.add(fund);
        return "created";
    }

    @DeleteMapping("/{fundCode}")
    public String delete(@PathVariable("fundCode") String fundCode){
        fundService.delete(fundCode);
        return "deleted";
    }

    @PutMapping("/{fundCode}")
    public String update(@PathVariable("fundCode")String fundCode,@RequestBody Fund fund){
        if (fundCode.equals(fund.getFundCode())){
            fundService.update(fund);
            return "updated";
        } else {
            return "update failed";
        }
    }

    @GetMapping("/{fundCode}")
    public Fund getFund(@PathVariable("fundCode")String fundCode){
        return fundService.get(fundCode);
    }

    @GetMapping("/hello/hello")
    public String hello(){
        return "hello";
    }
}
