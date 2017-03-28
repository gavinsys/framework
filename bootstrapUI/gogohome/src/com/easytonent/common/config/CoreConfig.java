package com.easytonent.common.config;

import java.io.File;

import com.easytonent.common.interceptor.GlobalActionInterceptor;
import com.easytonent.common.json.MyJson;
import com.easytonent.common.model._MappingKit;
import com.easytonent.controller.AppCustomerController;
import com.easytonent.controller.AppDriverController;
import com.easytonent.controller.CustomerController;
import com.easytonent.controller.DriverController;
import com.easytonent.controller.IndexController;
import com.easytonent.controller.OrderController;
import com.easytonent.controller.UserController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.json.IJsonFactory;
import com.jfinal.json.Json;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class CoreConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("config.txt");
		me.setViewType(ViewType.JSP);
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setBaseDownloadPath(PathKit.getWebRootPath()+File.separator+"WEB-INF"+File.separator+"files");
		me.setBaseUploadPath(PathKit.getWebRootPath()+File.separator+"WEB-INF"+File.separator+"files");
		me.setJsonFactory(new IJsonFactory() {public Json getJson() { return new MyJson();}});
		me.setBaseViewPath("/WEB-INF/view/");
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/platform", IndexController.class, "common"); // 第三个参数为该Controller的视图存放路径
		me.add("/platform/user", UserController.class, "user"); // 第三个参数省略时默认与第一个参数值相同，在此即为 // "/user"
		me.add("/platform/driver", DriverController.class, "driver");
		me.add("/platform/customer", CustomerController.class, "customer");
		me.add("/platform/order", OrderController.class, "order");
		me.add("/platform/appd", AppDriverController.class);
		me.add("/platform/appc", AppCustomerController.class);
	}

	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = createC3p0Plugin();
		c3p0Plugin.setInitialPoolSize(20);
		c3p0Plugin.setMinPoolSize(10);
		c3p0Plugin.setMaxPoolSize(30);
		c3p0Plugin.setMaxIdleTime(60);
		c3p0Plugin.setMaxIdleTime(60);
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);

		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 添加控制层全局拦截器
		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8087, "/", 3);
	}

}
