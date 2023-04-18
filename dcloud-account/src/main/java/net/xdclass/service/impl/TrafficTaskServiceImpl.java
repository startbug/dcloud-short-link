package net.xdclass.service.impl;

import net.xdclass.model.TrafficTaskDO;
import net.xdclass.mapper.TrafficTaskMapper;
import net.xdclass.service.TrafficTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流量包任务表 服务实现类
 * </p>
 *
 * @author Starbug
 * @since 2023-04-18
 */
@Service
public class TrafficTaskServiceImpl extends ServiceImpl<TrafficTaskMapper, TrafficTaskDO> implements TrafficTaskService {

}
