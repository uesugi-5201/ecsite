//package宣言
package com.internous.study.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.internous.study.model.dao.GoodsRepository;
import com.internous.study.model.dao.UserRepository;
import com.internous.study.model.entity.Goods;
import com.internous.study.model.entity.User;
import com.internous.study.model.form.GoodsForm;
import com.internous.study.model.form.LoginForm;

//@Controllerアノテーションで
//URLでアクセスできるように設定　(URL=localhost:8080/ecsite/admin/)
@Controller
@RequestMapping("/ecsite/admin")

public class AdminController {
	
	//@Autowiredフィールド単位で付与する。
	//付与されたフィールドの型と合うBeanを自動的にDIしてくれる。
	//型と合うBeanが複数ある場合は、@Qualifierアノテーションを使用して一意に識別させる。
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	//indexに遷移するメソッド
	//メソッドに対してクライアントからリクエストのあったURLをマッピングさせる。
	@RequestMapping("/")
	public String index() {
		return"adminindex";
	}
	//
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model m) {
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		if(users !=null && users.size() > 0) {
			boolean isAdmin = users.get(0).getIsAdmin() != 0;
			if(isAdmin) {
				List<Goods> goods = goodsRepos.findAll();
				m.addAttribute("userName", users.get(0).getUserName());
				m.addAttribute("password", users.get(0).getPassword());
				m.addAttribute("goods", goods);
			}
		}
		return "welcome";
	}
	//メソッドに対してクライアントからリクエストのあったURLをマッピングさせる。
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		return "goodsmst";
	}
	
	//メソッドに対してクライアントからリクエストのあったURLをマッピングさせる。
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		Goods goods = new Goods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		goodsRepos.saveAndFlush(goods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	//Controllerクラスのメソッド単位で付与。
	//戻り値をjson形式で返却することを意味する。
	//@ResponseBodyを使う場合は@ResponseBodyは不要になる。
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsRepos.deleteById(f.getId());
		} catch (IllegalArgumentException e) {
			return "-1";
		}
		return "1";
	}
}
