<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${urlParas == null}">
	<c:set var="urlParas" value=""/>
</c:if>

<c:if test="${(totalPage > 0) && (currentPage <= totalPage)}">
	<c:set var="startPage" value="${currentPage - 4}"/>
	<c:if test="${startPage < 1}">
		<c:set var="startPage" value="1"/>
	</c:if>
	
	<c:set var="endPage" value="${currentPage + 4}"/>
	<c:if test="${endPage > totalPage}">
		<c:set var="endPage" value="${totalPage }"/>
	</c:if> 
<nav class="text-right">
  <ul class="pagination">
		<c:if test="${currentPage <= 8}">
			<c:set var="startPage" value="1"/>
		</c:if>
		
		<c:if test="${(totalPage - currentPage) < 8}">
			<c:set var="endPage" value="${totalPage }"/>
		</c:if>
		
		<c:choose>
			<c:when test="${currentPage  == 1}">
				<li class="disabled">
			      <a aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			</c:when>
			<c:otherwise>
				<li>
			      <a href="${actionUrl}${currentPage - 1}${urlParas}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${currentPage > 8}">
			<li><a href="${actionUrl }${1}${urlParas}">${1 }</a></li>
    		<li><a href="${actionUrl }${2}${urlParas}">${2 }</a></li>
		</c:if>
		
		<c:forEach begin="${startPage }" end="${endPage }" var="i">
			<c:choose>
				<c:when test="${currentPage == i}">
					<li class="active"><a>${i } <span class="sr-only">(current)</span></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${actionUrl }${i}${urlParas}">${i }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${(totalPage - currentPage) >= 8}">
			<li><a href="${actionUrl }${totalPage - 1}${urlParas}">${toatalPage - 1}</a></li>
			<li><a href="${actionUrl }${totalPage}${urlParas}">${totalPage }</a></li>
		</c:if>
		
		<c:choose>
			<c:when test="${currentPage == totalPage}">
				<li class="disabled">
			      <a aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			</c:when>
			<c:otherwise>
				<li>
			      <a href="${actionUrl }${currentPage + 1}${urlParas}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>