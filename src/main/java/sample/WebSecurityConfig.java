package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import sample.security.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * このメソッドは認証には関係ない。
	 * permitAll()しているURL以外にアクセスされた時、ログインページへ遷移して認証が求められるようにする。
	 * 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// 認証無しでアクセスできるパスを指定する。
				.antMatchers("/", "/index", "/input").permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				// ログインフォームのパスを指定する(指定しない場合、デフォルトログインページが表示される)。
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				// ログアウトページ
//				.logoutUrl("/logout") 
				// ログアウト成功時の遷移先パスを指定する(指定しない場合、ログインページが表示される)。
//				.logoutSuccessUrl("/index") 
				.permitAll();
	}

	/**
	 * 認証の自動処理を呼び出す。
	 * 
	 * @param auth
	 * @throws Exception
	 */
/*
	@Autowired
	public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
		auth
			//ユーザID・PWを設定する。
			.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
*/
	
    /**
     * 認証の独自処理を呼び出す。
     *
     * @param auth
     * @throws Exception
     */

    @Autowired
    public void configureGlobal2(AuthenticationManagerBuilder auth) throws Exception {
        auth
            // ユーザー認証処理
            .userDetailsService(myUserDetailsService());
            // パスワード認証処理(入力されたパスワードを暗号化する。記述しない場合はベタパスワードのままDBに問い合わせる)
//            .passwordEncoder(new ShaPasswordEncoder(256));
    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }
}