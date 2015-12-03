package sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import sample.handler.MyHandlerInterceptor;

/**
 * コントローラクラスを介さずにログイン画面を表示させるため、ViewController URLとテンプレートをマッピングするクラス
 * (ブラウザからアクセスされたurlによって、表示するページを設定するクラス) (ブラウザから入力されるurlと、htmlファイルとを関連づける)
 *
 * @author f-konashi
 */
@Configuration
public class MvcConfig
		extends WebMvcConfigurationSupport /* extends WebMvcConfigurerAdapter */ {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/test").setViewName("test");
		registry.addViewController("/input").setViewName("input");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/shoppingcart").setViewName("shoppingcart");
		registry.addViewController("/mypage").setViewName("mypage");
	}

	// 初期化処理
	/**
	 * {@inheritDoc}
	 */

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myHandlerInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public MyHandlerInterceptor myHandlerInterceptor() {
		return new MyHandlerInterceptor();
	}

}
