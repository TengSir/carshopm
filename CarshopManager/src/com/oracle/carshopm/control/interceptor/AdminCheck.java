package com.oracle.carshopm.control.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AdminCheck extends MethodFilterInterceptor {
	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		System.out.println("您当前的请求是后台的管理业务方法，进入到拦截器了需要判断是否登陆");
		if (ActionContext.getContext().getSession().get("username") == null) {
			System.out.println("未登陆");
			return "checkFail";
		} else {
			System.out.println("已登陆");
			return ai.invoke();// 继续执行整个业务链条的下一个业务组件
		}
	}

}
