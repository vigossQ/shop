package cn.wolfcode.shop.qo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luohaipeng on 2018/2/13.
 */
@Setter@Getter
public class PageResult extends QueryObject{

    //具体的数据
    private List data;
    //总数据量
    private Integer totalCount;
    //总页数
    private Integer totalPage;
    //上一页
    private Integer prevPage;
    //下一页
    private Integer nextPage;


    public PageResult(){}

    /**
     *
     * @param data
     * @param totalCount
     * @param currentPage
     * @param pageSize
     */
    public PageResult(List data,Integer totalCount,Integer currentPage,Integer pageSize){
        this.data = data;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = this.totalCount % this.pageSize == 0 ?
                this.totalCount / this.pageSize :
                this.totalCount / this.pageSize + 1;
    }

    public static PageResult empty(){
        return new PageResult(new ArrayList(),0,0,0);
    }
}
