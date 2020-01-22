package com.biz.ems.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}
	
	public Identifier convertToSnakeCase(Identifier identifier) {
		if(identifier == null) {
			return identifier;
		}
		
		String regex = "([a-z])([A-Z])"; // 문자열에 영문자가 포함되어 있으면
		String replacement = "$1_$1";
		
		String newName = identifier.getText()
				.replaceAll(regex, replacement)
				.toLowerCase();
		
		return Identifier.toIdentifier(newName);
	}

}
