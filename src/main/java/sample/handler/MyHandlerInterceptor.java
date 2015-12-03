package sample.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import sample.model.ItemInfo;
import sample.service.ItemInfoService;

/**
 * @author f-konashi
 *
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
	@Autowired
	private ItemInfoService itemInfoService;
	
	/**
	 * 共通処理を記述する。 {@inheritDoc}
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 商品一覧を呼び出す
		List<ItemInfo> itemInfoList = itemInfoService.getItemAll();
		// 商品一覧をリクエストスコープに格納する
		request.setAttribute("itemInfoList", itemInfoList);

		return true;
	}

	/**
	 * 共通処理を記述する。 {@inheritDoc}
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		// 空実装
	}

	/**
	 * 共通処理を記述する。 {@inheritDoc}
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view)
			throws Exception {
		// 空実装
	}
}