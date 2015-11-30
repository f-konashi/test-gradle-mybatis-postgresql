package sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.Testtable;
import sample.service.TesttableService;

@Controller
@EnableAutoConfiguration
public class TestController {
	@Autowired
	// private TesttableService testtableService;
	private TesttableService testtableService = new TesttableService();
	
	/**
	 * testtableにユーザー情報を登録する
	 * 
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model, @RequestParam("id") String id, @RequestParam("name") String name) {
		// 入力されたデータを、エンティティークラスに格納する
		Testtable testtable = new Testtable();
		testtable.setId(id);
		testtable.setName(name);

		testtableService.insertOne(testtable);
		return "input";
	}

	/**
	 * testtableの全件データを画面に出力する。
	 * 
	 */
	@RequestMapping(value = "/test")
	public String selectAll(Model model) {
		List<Testtable> testtableList = testtableService.getTesttableAll();
		model.addAttribute("testtableList", testtableList);
		return "test";

	}

}