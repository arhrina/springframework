<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="img_panel img_card" data-file="${img.img_file}" data-seq="${img.img_seq}">
	<img src="${rootPath}/images/${img.img_file}" height="80%">
	<div>
		<h4>${img.img_title}</h4>
	</div>
<!-- <p>이미지보기</p>  -->
</div>

