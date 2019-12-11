package com.biz.pet.domain.pet_rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rfcOpenApi")
// XML로 http 헤더를 변경해서 자료 받아올 때 사용하는 Annotation. java 1.7 이상에서만 작동
// jaxb dependency(pom.xml)에 추가해주면 된다. 내부에서 xml을 처리해주는 엔진
public class RestVO {
	public RestBody body;
}
