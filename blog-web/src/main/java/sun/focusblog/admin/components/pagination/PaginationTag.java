package sun.focusblog.admin.components.pagination;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by root on 2015/12/13.
 * <p/>
 * Custom pagination tag
 */
public class PaginationTag extends SimpleTagSupport {
    private int num = 1;        // current page number
    private int size = 10;      // page size
    private int count = 0;      //  total data count
    private int pageCount = 0;  // page total count
    private int preNum = 1;     // preview page number
    private int nextNum = 1;    // next page number
    private int labelNum = 10;  // pagination label number
    private String params;      // url params
    private String url;         // link url


    @Override
    public void doTag() throws JspException, IOException {

//        PageContext pageContext = (PageContext) this.getJspContext();
//        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        pageCount = (count + size - 1) / size;
        if (pageCount == 1) {
            return;
        }
        if (num > 1) {
            preNum = num - 1;
        } else {
            preNum = 1;
        }

        if (num < pageCount) {
            nextNum = num + 1;
        } else {
            nextNum = pageCount;
        }
        if (StringUtils.isEmpty(url)) {
            url = "/";
        }
        if (params != null) {
            url += "?" + params + "&";
        }

        int span = labelNum;
        int index;
        if (pageCount - num <= size) {
            span = pageCount % size;
        }
        // compute first label number
        if (num % size == 1) {
            index = num;
        } else if (num % size == 0) {
            index = num - size + 1;
        } else {
            index = num - (num % size) + 1;
        }

        StringBuilder sb = new StringBuilder();
        if (count == 0) {
            sb.append("<span class=\"site-mini-font\" style=\"margin-right:10px;\">暂无任何内容</span>");
        } else {
            sb.append("<span class=\"site-mini-font\" style=\"margin-right:10px;\">").append(count).append("条数据 共").append(pageCount).append("页</span>");
            sb.append("<div class=\"ui pagination menu\">");
            for (int i = 0; i < span; i++) {
                String tempUrl = url + "num=" + (index + i);
                String flag = ((i + index) == num) ? "active " : "";
                sb.append("<a class=\"").append(flag).append("item\" href=\"").append(tempUrl).append("\">");
                sb.append((index + i));
                sb.append("</a>");
            }
            sb.append("</div>");
        }
        getJspContext().getOut().println(sb.toString());
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPreNum() {
        return preNum;
    }

    public void setPreNum(int preNum) {
        this.preNum = preNum;
    }

    public int getNextNum() {
        return nextNum;
    }

    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
