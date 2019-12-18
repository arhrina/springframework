package com.biz.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.product.domain.ProductDTO;
import com.biz.product.service.FileServiceV2;
import com.biz.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {

	@Autowired
	FileServiceV2 fService;
	@Autowired
	ProductService pService;

	// @ResponseBody
	@RequestMapping(value = "plist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8;")

	public String getPlist(Model model) {
		List<ProductDTO> proList = pService.selectAll();
		model.addAttribute("PLIST", proList);
		return "home";
	}

	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String input(@ModelAttribute ProductDTO proDTO, @RequestParam("u_file") MultipartFile u_file) {
		log.debug("상품정보입력 : " + proDTO.toString());
		log.debug("업로드한 파일 : " + u_file.getOriginalFilename());

		// 파일이 선택되지 않았으면 파일에 관한 처리를 수행하지 않는다. 선택되었을 경우만 if 안이 동작한다
		if (!u_file.isEmpty()) {
			// update 수행할 때 이미 upload된 파일이 있으면 기존의 파일을 삭제하고 새로운 파일을 업로드해야하므로
			// p_file 변수를 검사하여 값이 있으면 파일을 삭제
			if (proDTO.getP_file() != null) { // 파일이 저장되어 있다면
				fService.fileDelete(proDTO.getP_file());
			}

// web에서 파일이 전송되어 오면 fService.fileUp() 메서드에게 파일을 어딘가에 저장해달라고 요청
			String upFileName = fService.fileUp(u_file);
// 정상적으로 저장이 완료되면(!= null) 파일이름을 proDTO p_file변수에 저장하고 insert와 update 수행
			if (upFileName != null) {
				proDTO.setP_file(upFileName);
			}
		}

		int ret = 0;
		if (proDTO.getP_code().isEmpty()) { // p_code를 isEmpty로 비교해서 insert와 update를 실행할 수 있다
			log.debug("새로운 상품 등록"); // insert 실행
			ret = pService.insert(proDTO);
		} else {
			log.debug("기존 상품 변경"); // update 실행
			ret = pService.update(proDTO);
		}
		return "redirect:/plist"; // 작업을 완료시키고 selectAll을 다시 하여 자료가 들어갔는지 보기 위해 plist.jsp로 다시 보낸다
	}
	/*
	 * ResponseBody는 결과물을 view(*.jsp)로 보내지 않고 문자열 그대로 또는 객체(vo, dto)를 json 형태로 변경하여
	 * client에게 response를 수행하는 기능
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="getProduct", method=RequestMethod.GET, produces =
	 * "application/json;charset=UTF-8") public ProductDTO getProduct(String p_code)
	 * { ProductDTO proDTO = pService.findByPCode(p_code);
	 * 
	 * return proDTO; }
	 */

	@ResponseBody
	@RequestMapping(value = "plist/name", method = RequestMethod.GET, produces = "application/json;charset=UTF-8;")

	public List<ProductDTO> getNames(String p_name) {
		List<ProductDTO> proList = pService.findByPNames(p_name);
		return proList;
	}

	/*
	 * produces의 content-Type 서버에서 문자열, 객체 등등의 실제 데이터를 response할때 어떤 type으로 보낼것인가를
	 * 나타내는 문자열
	 */
	@ResponseBody
	@RequestMapping(value = "pname", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
	public String getPName(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);
		// return proDTO.getP_name();
		return "<h1>" + proDTO.getP_name() + "</h1>";

	}

	@ResponseBody
	@RequestMapping(value = "oprice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getOPrice(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);
		return proDTO.getP_oprice() + "";

	}

	@ResponseBody
	@RequestMapping(value = "iprice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getIPrice(String p_code) {

		ProductDTO proDTO = pService.findByPCode(p_code);
		return proDTO.getP_iprice() + "";
	}
	@RequestMapping(value="imgDelete", method=RequestMethod.GET)
	public String imgDelete(String p_code) {
		ProductDTO proDTO = pService.findByPCode(p_code);
		if(!proDTO.getP_file().isEmpty()) {
			fService.fileDelete(proDTO.getP_file());
			proDTO.setP_file(null);
			pService.update(proDTO);
		}
		return "redirect:/plist";
	}
	
	/*
	 * ResponseBody는 문자열이나 객체에 담긴 data를 그대로 client에게 전송 ResponseBody를 사용할 때는
	 * produces를 설정해주는 것이 좋다. 특히, 한글데이터를 response할 때는 반드시 charset=UTF-8 입력
	 */
	@ResponseBody
	@RequestMapping(value = "getString", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getString(@RequestParam(value = "str", // query로 보내는 변수명
			// required=false와 defaultValue가 없으면 server는 client에게 400 오류를 보내고 처리를 거부. vo와
			// dto에는 적용할 수 없음
			required = false, // 혹시 값을 보내지 않아도 오류를 내지마라
			defaultValue = "없음" // 값이 없으면 없음이라는 문자열을 세팅
	) String myStr) {
		if (myStr.equals("없음")) {
			return "http://localhost:8080/product/getString?str=문자열 형식으로 보내세요";
		} else {
			return "보낸 문자열은 " + myStr;
		}
	}
}
