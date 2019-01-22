package com.qrmg.zd.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.frame.bean.OutputObject;
import com.qrmg.zd.model.Person;
import com.qrmg.zd.service.impl.ChannelServiceImpl;
import com.qrmg.zd.service.impl.PersonServiceImpl;
import com.qrmg.zd.util.StringUtil;

/**
 * @Description: 用户信息
 * @ClassName: PersonController  
 * @author zz
 * @date 2019年1月22日 下午2:50:55
 */
@RestController
@RequestMapping(value="/person")
public class PersonController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="personService")
	private PersonServiceImpl personService;
	@Resource(name="channelService")
	private ChannelServiceImpl channelService;
	
	/**
	 * @Description: 用户登记
	 * @author zz
	 * @date 2019年1月22日 下午2:54:01
	 * @return 
	 * @param
	 */
	@RequestMapping(value="/addPerson", method=RequestMethod.GET)
	public OutputObject addPerson(HttpServletRequest request){
		OutputObject output = new OutputObject();
		String name = request.getParameter("userName");
		String phone = request.getParameter("userPhone");
		String channel = request.getParameter("channelCode");
		if(StringUtil.isEmpty(channel)){
			output.setReturnCode("9999");
			output.setReturnMessage("参数不符合规范！");
			return output;
		}
		try {
			Person person = new Person();
			person.setUserName(name);
			person.setUserPhone(phone);
			person.setChannelCode(channel);
			personService.addPersonRegister(person);
		} catch (Exception e) {
			output.setReturnCode("9999");
			output.setReturnMessage("登记失败！");
		}
		Map<String, String> map = new HashMap<>();
		map.put("channelCode", channel);
		output.setBean(channelService.getChannel(map));
		output.setReturnCode("0");
		return output;
	}
	
}
