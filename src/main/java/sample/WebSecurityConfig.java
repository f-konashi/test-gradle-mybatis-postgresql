package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import sample.security.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  /**
   * 認証には関係ないメソッドです.
   * permitAll()しているURL以外にアクセスされた時、ログインページへ遷移して認証が求められるようにします.
   * 
   * @param http http
   * @throws Exception Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests() // 認証対象外のパスを設定する.
        .antMatchers("/", "/index", "/input", "/addUser", "/regist")  // 認証対象外のパスを設定する.
        .permitAll()  // 上記パスへのアクセスを許可する.
        .anyRequest()  // その他のリクエストは認証がに必要.
        .authenticated()
        .and()
      .formLogin()
        .loginPage("/login")  // ログインフォームのパスを指定する(指定しない場合、デフォルトログインページが表示される).
        .permitAll()  // 上記パスへのアクセスを許可する.
        .and()
      .logout()
        .logoutUrl("/logout") // ログアウトページ.
        .logoutSuccessUrl("/index")  // ログアウト成功時の遷移先パスを指定する(指定しない場合、ログインページが表示される).
        .deleteCookies("JSESSIONID")  // ログアウト時にセッションIDをクッキーから削除する.
        .invalidateHttpSession(true)  // セッションを破棄する.
        .permitAll();
  }

  /**
   * 認証の自動処理を呼び出すメソッドです.
   * 
   * @param auth auth
   * @throws Exception Exception
   */
  /*
  @Autowired
  public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
    //ユーザID・PWを設定する
    auth
      .inMemoryAuthentication().withUser("user").password("password").roles("USER");
  }
  */
  /**
   * 認証の独自処理を呼び出すメソッドです.
   * 
   * @param auth auth
   * @throws Exception Exception
   * 
   */
  @Autowired
  public void configureGlobal2(AuthenticationManagerBuilder auth) throws Exception {
    auth
      // ユーザー認証処理
      .userDetailsService(myUserDetailsService())
      // パスワード認証処理(入力されたパスワードを暗号化する。記述しない場合は入力されたパスワードのままDBに問い合わせる)
      //.passwordEncoder(new ShaPasswordEncoder(256));
      .passwordEncoder(new StandardPasswordEncoder());
  }

  @Bean
  public UserDetailsService myUserDetailsService() {
    return new MyUserDetailsService();
  }
}