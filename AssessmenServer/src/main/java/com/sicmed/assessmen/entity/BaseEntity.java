package com.sicmed.assessmen.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Setter
@Getter
public class BaseEntity{

    @JsonIgnore
    private int pageSize;

    @JsonIgnore
    private int pageNo;

    @JsonIgnore
    private int beginNo;

    @JsonIgnore
    private final static int PAGE_SIZE = 10;

    @JsonIgnore
    private final static int PAGE_NO = 0;

    public BaseEntity() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");

        if (StringUtils.isNotBlank(pageSizeStr)) {
            int size = Integer.parseInt(pageSizeStr);
            this.pageSize = size > 0 ? size : PAGE_SIZE;

        } else {
            this.pageSize = PAGE_SIZE;
        }

        if (StringUtils.isNotBlank(pageNoStr)) {
            int number = Integer.parseInt(pageNoStr);
            this.pageNo = number > 0 ? number - 1 : PAGE_NO;
        } else {
            this.pageNo = PAGE_NO;
        }

        this.beginNo = pageNo * pageSize;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", beginNo=" + beginNo +
                '}';
    }
}
