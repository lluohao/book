package com.code.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 分页标签
 * 
 * @author cc
 * 
 */
public class PagingTag extends BodyTagSupport {
	private String width;

	private String urlName;
	private String pageAllName;
	private String pageNoName;
	private String loadDivIdName;
	private int count;

	public void setPageNoName(String pageNoName) {
		this.pageNoName = pageNoName;
	}

	public void setLoadDivIdName(String loadDivIdName) {
		this.loadDivIdName = loadDivIdName;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public void setPageAllName(String pageAllName) {
		this.pageAllName = pageAllName;
	}

	public int doEndTag() throws JspException {

		JspWriter out = this.pageContext.getOut();
		ServletRequest req = this.pageContext.getRequest();
		int selectPageNo = (Integer) req.getAttribute(pageNoName);
		int pageAll = (Integer) req.getAttribute(pageAllName);
		int width = Integer.parseInt(this.width.split("px")[0]);
		count = (width - 250) / 27 + 1;
		try {
			out.print("<style type='text/css'>		");
			out.print("#paging {		");
			out.print("float:left;");
			out.print("width:" + this.width + ";");
			out.print("	margin-top: 40px;		");
			out.print("height:50px;");
			out.print("}		");
			out.print("#pageNo1{");
			out.print("margin-left:"
					+ (pageAll > count ? "5px" : (count - pageAll) * 15 + "px"));
			out.print("}");
			out.print(".pageItem{		");
			out.print("	margin-left: 5px;		");
			out.print("	width: 20px;		");
			out.print("	float: left;		");
			out.print("text-align:center;");
			out.print("}		");
			out.print("#pageNo" + selectPageNo + "{");
			out.print("background-color:#A6978A");
			out.print("}");
			out.print("</style>		");
			out.print("<div id='paging'>");
			if (selectPageNo >= count) {
				out.print("<a class='pageItem' id='pageNo1' href='javascript:toPage(1)'>"
						+ 1 + "</a>");
				out.print("<span class='pageItem'>...</span>");
				if ((pageAll - selectPageNo + count / 2) > count) {
					for (int i = selectPageNo - count / 2; i < selectPageNo
							+ count - count / 2; i++) {
						out.print("<a class='pageItem' id='pageNo" + i
								+ "' href='javascript:toPage(" + i + ")'>" + i
								+ "</a>");
					}
					out.print("<span class='pageItem'>...</span>");
					out.print("<a class='pageItem' id='pageNo" + pageAll
							+ "' href='javascript:toPage("
							+ req.getAttribute(pageAllName) + ")'>"
							+ req.getAttribute(pageAllName) + "</a>");
				} else {
					for (int i = pageAll - count; i <= pageAll; i++) {
						out.print("<a class='pageItem' id='pageNo" + i
								+ "' href='javascript:toPage(" + i + ")'>" + i
								+ "</a>");
					}
				}
			} else if (count < pageAll) {
				for (int i = 1; i <= count; i++) {

					out.print("<a class='pageItem' id='pageNo" + i
							+ "' href='javascript:toPage(" + i + ")'>" + i
							+ "</a>");
				}
				out.print("<span class='pageItem'>...</span>");
				out.print("<a class='pageItem' id='pageNo" + pageAll
						+ "' href='javascript:toPage("
						+ req.getAttribute(pageAllName) + ")'>"
						+ req.getAttribute(pageAllName) + "</a>");
			} else {
				for (int i = 1; i <= pageAll; i++) {
					out.print("<a class='pageItem' id='pageNo" + i
							+ "' href='javascript:toPage(" + i + ")'>" + i
							+ "</a>");
				}
			}
			out.print("<span class='pageItem'>第</span><input class='pageItem' type='text' id='pageInput' "
					+"/><span class='pageItem'>页</span>");
			out.print("<a href='javascript:toPageByInput()' class='pageItem'>OK</a>");
			out.print("<script type='text/javascript'>");
			out.print("function toPageByInput(){");
			out.print("	var pageNo=$('#pageInput').val();");
			out.print("	$('#" + loadDivIdName + "').load('" + urlName
					+ "?pageNo='+pageNo);");
			out.print("}");
			out.print("function toPage(pageNo){");
			out.print("	$('#" + loadDivIdName + "').load('" + urlName
					+ "?pageNo='+pageNo);");
			out.print("}");
			out.print("</script>");
			out.print("</div>		");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
