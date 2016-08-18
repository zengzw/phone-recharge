package com.tsh.vas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dtds.platform.util.bean.Result;
import com.dtds.platform.util.exception.AopException;
import com.dtds.platform.util.oss.OSSClientUtil;
import com.dtds.platform.util.security.UserInfo;
import com.dtds.platform.web.controller.PlatformController;
import com.dtds.platform.web.userinfo.UserUtil;

@Scope("prototype")
@SuppressWarnings("all")
public class BaseController extends PlatformController{
	@Override
	public Result getResult() {
		return getResult(this.request);
	}

	public Result getResult(HttpServletRequest request) {
		UserInfo userInfo = UserUtil.getUserInfo(request);
		String teamName = "vas";
		Result result = new Result(request, userInfo, teamName);
		return result;
	}
	
	/**
	 * 图片上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadimg.do")
	@ResponseBody
	public Map<Object, Object> uploadimg(@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Result result = this.getResult();
		try {
			OSSClientUtil o = new OSSClientUtil();
			if(file.isEmpty()){
				return null;
			}
			String fileName = "";
			String suffix = OSSClientUtil.getSuffix(file.getOriginalFilename());
			InputStream is=null;
			
			is = file.getInputStream();
			fileName = o.putImage(is,suffix);
			is.close();
				
			map.put("fileName", fileName);
			map.put("path", o);
		} catch (Exception e) {
			this.error(result, e);
		} finally{
			this.send(result);
		}
		return map;
	}
	
}
