package com.roadhourse.spacepal.model;

import com.google.gson.annotations.SerializedName;

public class Role{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("htmlDescription")
	private String htmlDescription;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHtmlDescription(String htmlDescription){
		this.htmlDescription = htmlDescription;
	}

	public String getHtmlDescription(){
		return htmlDescription;
	}

	@Override
 	public String toString(){
		return 
			"Role{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",htmlDescription = '" + htmlDescription + '\'' + 
			"}";
		}
}