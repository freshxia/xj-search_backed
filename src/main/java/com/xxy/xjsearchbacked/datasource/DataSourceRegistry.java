package com.xxy.xjsearchbacked.datasource;

import com.xxy.xjsearchbacked.model.enums.SearchCategoryEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataSourceRegistry {
    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PostDataSource postDataSource;
    private Map dataSourceMap;
    @PostConstruct
    void initDataSource() {
        dataSourceMap = new ConcurrentHashMap (){{
                put(SearchCategoryEnum.POST.getValue(),postDataSource);
                put(SearchCategoryEnum.USER.getValue(),userDataSource);
                put(SearchCategoryEnum.PICTURE.getValue(),pictureDataSource);
            }};
    }

    public DataSource GetDataSourceByType(String type) {
        if (dataSourceMap == null) {
            return null;
        }
        return (DataSource) dataSourceMap.get(type);
    }
}
