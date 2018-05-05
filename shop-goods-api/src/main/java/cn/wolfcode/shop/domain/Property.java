package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Property extends BaseDomain {

    private Long catalogId;

    private String name;

    private Integer sort;

    private Byte type;

    private List<PropertyValue> propertyValueList;

    public String getJsonData() {
        JSONObject map = new JSONObject();
        map.put("id", this.getId());
        map.put("name", this.name);
        map.put("sort", this.sort);
        map.put("type", this.type);
        return JSON.toJSONString(map);
    }
}