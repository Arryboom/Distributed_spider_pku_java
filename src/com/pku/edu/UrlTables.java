package com.openzone.search.spider;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class UrlTables {
	private static Set<String> visitedUrlSet=new HashSet();
	private static LinkedList unvisitedUrlSet=new LinkedList();
	public static Set getVisitedUrl() {
		return visitedUrlSet;
	}
	public static void setVisitedUrl(Set visitedUrl) {
		UrlTables.visitedUrlSet = visitedUrl;
	}
	public static LinkedList getUnvisitedUrl() {
		return unvisitedUrlSet;
	}
	public static void setUnvisitedUrl(LinkedList unvisitedUrl) {
		UrlTables.unvisitedUrlSet = unvisitedUrl;
	}
	public static void addToVisitedUrlSet(String url){
		visitedUrlSet.add(url);
	}
	public static boolean IsUnvisitedUrlSetEmpty(){
		boolean isEmpty=false;
		if(unvisitedUrlSet.isEmpty()){
			isEmpty=true;
		}
		return isEmpty; 
	}
	public static void addToUnvisitedUrlSet(Set<String> urls){
		for (String url : urls) {
			if(!isVisited(url)){
				unvisitedUrlSet.add(url);
			}
		}
	}
	public static boolean isVisited(String url){
		boolean isVisited=false;
		for (String visitedUrl : visitedUrlSet) {
			if(visitedUrl.equals(url)){
				isVisited=true;
			}
		}
		return isVisited;
	}
	public static String getFirstFromVisitedUrSet(){
		String url=unvisitedUrlSet.getFirst().toString();
		unvisitedUrlSet.removeFirst();
		return url;
	}
}
