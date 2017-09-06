/**
 */
package com.fade.mybatis.dto;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Description: 分页对象<br/>
 *
 * @author qingquanzhong
 * @date: 2017年09月02日 00:17:05
 * @version 1.0
 * @since JDK 1.87
 */
public class PageDto<T> implements Serializable {

    private static final long serialVersionUID = 2479081883631073018L;

    /** 结果列表 */
    private List<T> values;

    /** 总记录数 */
    private Integer totalCount;

    /** 每页数目 */
    private Integer pageSize;

    /** 页码 */
    private Integer pageNum;

    /** 总页数 */
    private Integer totalPages;

    /** 当前页记录数 */
    private Integer pageCout;
    
    @SuppressWarnings("unused")
    private PageDto(){
    }

    /** pageSize:每页数目,pageNum:页码,values:结果集,totalCount:总记录数 */
    public PageDto(Integer pageSize, Integer pageNum, List<T> values, Integer totalCount) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        doCheck();
        this.totalCount = totalCount;
        this.values = values == null ? Lists.newArrayList() :values;
        setTotalPage();
    }

    /**设置总记录数*/
    private void setTotalPage() {
        if (this.getTotalCount() == 0)
            this.totalPages = 0;
        else
            this.totalPages = this.getTotalCount() / this.getPageSize() + (this.getTotalCount() % this.getPageSize() == 0 ? 0 : 1);
    }
    
    private void doCheck() {
        if (null == this.pageSize || this.pageSize < 1) {
            throw new IllegalArgumentException("the pageSize value was illegal.");
        }
        if (null == this.pageNum || this.pageNum < 1) {
            throw new IllegalArgumentException("the pageNum value was illegal.");
        }
    }

    /** {@linkplain #values} */
    public List<T> getValues() {
        return null == values ? Lists.newArrayList() : values;
    }

    /** {@linkplain #totalCount} */
    public Integer getTotalCount() {
        if (null == totalCount || totalCount <= 0)
            totalCount = 0;
        return totalCount;
    }

    /** {@linkplain #pageSize} */
    public Integer getPageSize() {
        return pageSize;
    }

    /** {@linkplain #pageNum} */
    public Integer getPageNum() {
        return pageNum;
    }

    /** {@linkplain #totalPages} */
    public Integer getTotalPages() {
        return totalPages;
    }

    /** {@linkplain #pageCout} */
    public Integer getPageCout() {
        pageCout = null == values ? 0 : values.size();
        return pageCout;
    }

	@Override
	public String toString() {
		return "Page [values=" + values + ", totalCount=" + totalCount + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", totalPages=" + totalPages + ", pageCout=" + pageCout + "]";
	}

}
