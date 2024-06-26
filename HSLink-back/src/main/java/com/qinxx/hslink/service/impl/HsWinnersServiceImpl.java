package com.qinxx.hslink.service.impl;

import com.qinxx.hslink.dao.HsWinnersMapper;
import com.qinxx.hslink.model.HsWinners;
import com.qinxx.hslink.service.IHsWinnersService;
import com.qinxx.hslink.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by PHY
 * @Classname HsWinnersServiceImpl
 * @date 2021-06-02 11:40
 */
@Service
public class HsWinnersServiceImpl implements IHsWinnersService {

    @Resource
    private HsWinnersMapper hsWinnersMapper;

    /**
     * 查询获奖列表
     *
     * @param hsWinners 获奖
     * @return 获奖
     */
    @Override
    public List<HsWinners> selectHsWinnersList(HsWinners hsWinners)
    {
        return hsWinnersMapper.selectHsWinnersList(hsWinners);
    }

    /**
     * 新增获奖
     *
     * @param hsWinners 获奖
     * @return 结果
     */
    @Override
    public int insertHsWinners(HsWinners hsWinners)
    {
        hsWinners.setCreateTime(DateUtils.getNowDate());
        return hsWinnersMapper.insertHsWinners(hsWinners);
    }

    /**
     * 修改获奖
     *
     * @param hsWinners 获奖
     * @return 结果
     */
    @Override
    public int updateHsWinners(HsWinners hsWinners)
    {
        hsWinners.setUpdateTime(DateUtils.getNowDate());
        return hsWinnersMapper.updateHsWinners(hsWinners);
    }

    /**
     * 批量删除获奖
     *
     * @param ids 需要删除的获奖ID
     * @return 结果
     */
    @Override
    public int deleteHsWinnersByIds(Long[] ids)
    {
        return hsWinnersMapper.deleteHsWinnersByIds(ids);
    }

    /**
     * 删除获奖信息
     *
     * @param id 获奖ID
     * @return 结果
     */
    @Override
    public int deleteHsWinnersById(Long id)
    {
        return hsWinnersMapper.deleteHsWinnersById(id);
    }
}
