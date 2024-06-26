package com.qinxx.hslink.dao;

import com.qinxx.hslink.model.HsWinners;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author by PHY
 * @Classname HsWinnersMapper
 * @date 2021-06-02 11:42
 */
@Mapper
public interface HsWinnersMapper {

    /**
     * 查询获奖列表
     *
     * @param hsWinners 获奖
     * @return 获奖集合
     */
    public List<HsWinners> selectHsWinnersList(HsWinners hsWinners);

    /**
     * 新增获奖
     *
     * @param hsWinners 获奖
     * @return 结果
     */
    public int insertHsWinners(HsWinners hsWinners);

    /**
     * 修改获奖
     *
     * @param hsWinners 获奖
     * @return 结果
     */
    public int updateHsWinners(HsWinners hsWinners);

    /**
     * 删除获奖
     *
     * @param id 获奖ID
     * @return 结果
     */
    public int deleteHsWinnersById(Long id);

    /**
     * 批量删除获奖
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHsWinnersByIds(Long[] ids);
}
