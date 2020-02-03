package com.biz.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.biz.bbs.domain.MenuVO;

@Mapper
public interface MenuDao {
	
	@Select("SELECT * FROM tbl_menu WHERE menu_p_id is null ORDER BY menu_id")
	@Results(value= {
			@Result(property = "menu_id", column="menu_id"),
			@Result(property = "drop_menus", column="menu_id", javaType=List.class, many=@Many(select="getSubMenu"))
			// 1대 다 관계를 설정하는 many. List 하나하나에 getSubMenu를 실행
		}
	)
	public List<MenuVO> getAllMenu();
	
	@Select("SELECT * FROM tbl_menu WHERE menu_p_id = #{menuId}") // 서브메뉴를 가져오기 위한 코드
	public List<MenuVO> getSubMenu(String menuId);
}
