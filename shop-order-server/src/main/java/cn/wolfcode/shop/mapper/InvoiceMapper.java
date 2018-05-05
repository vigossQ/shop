package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Invoice;
import java.util.List;

public interface InvoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Invoice record);

    Invoice selectByPrimaryKey(Long id);

    List<Invoice> selectAll();

    int updateByPrimaryKey(Invoice record);
}