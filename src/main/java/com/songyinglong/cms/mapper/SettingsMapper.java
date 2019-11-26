package com.songyinglong.cms.mapper;

import com.songyinglong.cms.domain.Settings;
import com.songyinglong.cms.domain.SettingsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettingsMapper {
    long countByExample(SettingsExample example);

    int deleteByExample(SettingsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Settings record);

    int insertSelective(Settings record);

    List<Settings> selectByExample(SettingsExample example);

    Settings selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Settings record, @Param("example") SettingsExample example);

    int updateByExample(@Param("record") Settings record, @Param("example") SettingsExample example);

    int updateByPrimaryKeySelective(Settings record);

    int updateByPrimaryKey(Settings record);
}