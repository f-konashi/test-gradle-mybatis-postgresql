package sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * コントローラクラスを介さずにログイン画面を表示させるため、ViewController
 * URLとテンプレートをマッピングするクラス (ブラウザからアクセスされたurlによって、表示するページを設定するクラス)
 * (ブラウザから入力されるurlと、htmlファイルとを関連づける)
 *
 * @author f-konashi
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName("index");
       registry.addViewController("/test").setViewName("test");
       registry.addViewController("/input").setViewName("input");
       registry.addViewController("/login").setViewName("login");
   }
}
